package refresh.ManageSystem.service;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.vo.HolidayServiceVO;

/**
 * Daniel Kim
 * HolidayServiceTest : Open API 데이터 fetching, DB 연동에 대한 테스트 클래스
 * 2023-04-19
 */
@SpringBootTest
class HolidayServiceTest {
    @Autowired
    private HolidayService holidayService;


//    @Test
//    void 휴일_정보_삽입() {
//        holidayService.getHolidayData();
//    }

    /**
     * Daniel Kim
     *
     * 휴일 정보를 DB 에서 가져오는 Test
     *
     * 2023-04-19
     */
    @Test
    void 휴일_정보_DB_에서_가져오기() {
        // 0개의 휴일 테스트
        String year1 = "2023";
        String month1 = "11";
        List<HolidayServiceVO> holidayList1 = holidayService.holidayDBData(year1, month1);
        Assertions.assertThat(holidayList1.size()).isEqualTo(0);

        // 휴일이 있을때 테스트
        String year = "2023";
        String month = "5";
        List<HolidayServiceVO> holidayList2 = holidayService.holidayDBData(year, month);
        Assertions.assertThat(holidayList2.size()).isEqualTo(2);

    }
}