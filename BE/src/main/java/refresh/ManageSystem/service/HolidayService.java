package refresh.ManageSystem.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import refresh.ManageSystem.vo.HolidayServiceVO;
import refresh.ManageSystem.dao.HolidayApiDAO;
import refresh.ManageSystem.dao.HolidayDbDAO;
import refresh.ManageSystem.repository.HolidayRepository;
/**
 * Daniel kim
 *
 * @Service : 특별한 기능이 없지만 Bean 으로 관리되는 Component 서비스 계층이라는 것을 나타내는 Annotation
 * @Value({${key}) : key 에 해당하는 value 를 application-properties 에서 찾아서 적용
 * @Scheduled : Spring-Scheduler method 로 등록(cron job 표현식을 통해 스케줄링)
 * Refactoring 예정
 *
 * 2023-04-18
 */
@Service
public class HolidayService {
    @Autowired
    private HolidayRepository holidayRepository;
    @Value("${service-key}")
    private String serviceKey;
    private static List<HolidayApiDAO> result;
    private final static String array[] = {"response", "body", "items"};
    /**
     * Daniel Kim
     *
     * 1. 해당 스케줄일 때 호출 되는 메소드 (매년 1월 1일)
     * 2. result ArrayList 메모리 할당
     * 3. 데이터베이스 테이블 비우기
     * 4. 현재 연도와 내년에 대한 값 get (팩토리 빈으로 관리할 예정, 이유 위의 이유와 같음)
     * 5. 26번을 돌면서 올해 1 ~ 12월, 내년 1 ~ 12월의 데이터를 Open API 에서 데이터 Fetching
     * 6. DB repository Mybatis interface 에 구현되어 있는 Mybatis insert query 호출
     *
     * 2023-04-19
     */
    @Scheduled(cron = "0 0 0 1 1 *")
    public void getHolidayData() {
        try {
            result = new ArrayList<>();
            holidayRepository.removeHolidayTable();

            int curYear = LocalDate.now().getYear();
            int nextYear = curYear + 1;
            int curMonth = 1;
            int month;
            JsonNode rootNode;
            while(curMonth < 26) {
                month = curMonth % 13;
                if(month == 0){
                    curMonth++;
                    continue;
                }
                if((curMonth - 1) / 12  == 0) {
                    // 올해 데이터
                    rootNode = getJsonNode(curYear, month);
                    findData(0, rootNode);

                } else {
                    // 내년 데이터
                    rootNode = getJsonNode(nextYear, month);
                    findData(0, rootNode);
                }
                curMonth++;
            }

            for(HolidayApiDAO hdt : result) {
                holidayRepository.insertHoliday(hdt);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    /**
     * Daniel Kim
     *
     * Open API 에 요청을 보내는 메소드
     * URL 과 serviceKey 그리고 Query 를 만들어서 RestTemplate 을 통해 서버간 통신을 구현
     * Response 를 ResponseEntity 객체로 받아 body 만 추출해서 body Tree 를 JsonNode 로 변환하는 작업을
     * jackson 의 objectMapper 를 통해 구현했다.
     *
     * 2023-04-18
     */
    private JsonNode getJsonNode(int year, int month) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        String monthStr = month < 10 ? "0"+month : String.valueOf(month);
        String URL = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?ServiceKey=" + serviceKey + "&";
        String query = URL + "solYear=" + year + "&" + "solMonth=" + monthStr;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(query, HttpMethod.GET, entity, String.class);

        JsonNode rootNode = objectMapper.readTree(response.getBody());
        return rootNode;
    }
    /**
     * Daniel Kim
     *
     * JsonNode 의 모양은 response > body > items > item 으로 이루어져 있다.
     * depth 를 하나씩 내려가서 마지막 item 을 가져왔을때
     * ArrayNode 이면 여러개의 값이 들어있는 것 -> 탐색 가능
     * ObjectNode 이면 하나의 값이 들어있는 것 -> 값 추출
     * result 에 값을 저장하는 재귀 함수
     *
     * 2023-04-18
     */
    private static void findData(int dep, JsonNode jsonNode) throws JsonProcessingException {
        if(dep == 3) {
            JsonNode jn = jsonNode.get("item");
            if(jn instanceof ArrayNode) {
                for(JsonNode tmp: jn) {
                    result.add(HolidayApiDAO.builder()
                                            .dateName(tmp.get("dateName").toString())
                                            .locDate(tmp.get("locdate").toString()).build());
                }
            } else if(jn instanceof ObjectNode){
                result.add(HolidayApiDAO.builder()
                                        .dateName(jn.get("dateName").toString())
                                        .locDate(jn.get("locdate").toString()).build());
            }
            return;
        }
        findData(dep + 1, jsonNode.get(array[dep]));
    }
    /**
     * Daniel Kim
     *
     * DB 에서 값을 찾아오는 메소드
     * Mybatis 에서는 Parameter 가 여러 개일 때는 DAO 를 만드는 방법 or Map 을 통해 데이터를 전달한다.
     * repository 에서 findHoliday(Model) 로 Mybatis query 호출
     *
     * 2023-04-18
     */
    public List<HolidayServiceVO> holidayDBData(String strYear, String strMonth) {
        int month = Integer.parseInt(strMonth);
        String monthStr = month < 10 ? "0"+month : String.valueOf(month);
        return holidayRepository.findHoliday(HolidayDbDAO.builder()
                                                         .month(monthStr)
                                                         .year(strYear)
                                                         .build());
    }

}
