package refresh.ManageSystem.service;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.dto.HolidayServiceDTO;

/**
 * Daniel Kim
 * HolidayServiceTest : Open API 데이터 fetching, DB 연동에 대한 테스트 클래스
 * 2023-04-19
 */
@SpringBootTest
class HolidayServiceTest {
    @Autowired
    private HolidayService holidayService;


    @Test
    void 휴일_정보_삽입() {
        holidayService.getHolidayData();
    }

    /**
     * Daniel Kim
     *
     * 휴일 정보를 DB 에서 가져오는 Test
     *
     * 2023-04-19
     */
    @Test
    void 휴일_정보_DB_에서_가져오기() {
        String year = "2023";
        String month = "12";
        List<HolidayServiceDTO> holidayList = holidayService.holidayDBData(year, month);

    }
}