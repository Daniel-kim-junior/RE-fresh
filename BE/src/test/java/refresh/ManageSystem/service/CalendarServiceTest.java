package refresh.ManageSystem.service;


import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.dto.CalendarServiceDTO;
import refresh.ManageSystem.vo.HolidayServiceVO;

/**
 * Daniel Kim
 * 달력의 데이터를 만드는 서비스들을 모아놓은 클래스
 * 윤년 계산, 달력 데이터 etc
 * 2023-04-14
 */
@SpringBootTest
class CalendarServiceTest {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private HolidayService holidayService;

    /**
     * Daniel Kim
     *
     * 달력 데이터를 만드는 메소드
     *
     * 2023-04-15
     */
    @Test
    void 달력_list_계산() {
        List<CalendarServiceDTO> list = calendarService.updateCalendar("2023", "04", "개발팀");

        assertThat(list.size()).isEqualTo(42);
    }

    /**
     * Daniel Kim
     *
     * DB에서 가져온 공휴일 데이터를 이분 탐색을 통해
     * 제대로 들어왔는지 확인하는 메소드
     *
     * 2023-04-15
     */
    @Test
    void 이분_탐색_결과() {
        List<HolidayServiceVO> holidayServiceDTOS = holidayService.holidayDBData("2023", "05");
        boolean b1 = CalendarService.binarySearch(holidayServiceDTOS, 5);
        boolean b2 = CalendarService.binarySearch(holidayServiceDTOS, 27);
        boolean b3 = CalendarService.binarySearch(holidayServiceDTOS, 10);

        assertThat(b1).isTrue();
        assertThat(b2).isTrue();
        assertThat(b3).isFalse();
    }

    @Test
    /**
     * Daniel Kim
     *
     * 달력을 생성하는데 전달의 마지막 일자 정보가 필요하기 때문에 만든 메소드 테스트
     *
     * 2023-04-16
     */
    void 전달의_마지막_일자() {
        int preMonthEnd1 = calendarService.calendarEnd(2023, 3);
        assertThat(preMonthEnd1).isEqualTo(31);

        int preMonthEnd2 = calendarService.calendarEnd(2023, 2);
        assertThat(preMonthEnd2).isEqualTo(28);

        int preMonthEnd3 = calendarService.calendarEnd(2023, 1);
        assertThat(preMonthEnd3).isEqualTo(31);

        int preMonthEnd4 = calendarService.calendarEnd(2016, 2);
        assertThat(preMonthEnd4).isEqualTo(29);
    }
    @Test
    /**
     * Daniel Kim
     *
     * 매월 1일의 index를 생성하고 그 값이 실제 index와 맞는지 확인하는 메소드 테스트
     * [일요일, 월요일, 화요일, 수요일, 목요일, 금요일, 토요일]
     * [0,     1,    2 ,   3,    4,    5,    6]
     *
     * 2023-04-16
     */
    void 매월_1일_요일_계산() {

        // 2023년 4월 시작일 토요일

        int saturday = DayOfWeek.SATURDAY.getValue();
        assertThat(saturday).isEqualTo(calendarService.getThisMonthOneDay(2023, 4));

        // 2023년 10월 시작일 일요일

        int sunday = 0;
        assertThat(sunday).isEqualTo(calendarService.getThisMonthOneDay(2023, 10));


    }
    /**
     * Daniel Kim
     *
     * 윤년인 케이스와 윤년이 아닌 케이스를 판별하는 메소드 테스트
     *
     * 2023-04-16
     */
    @Test
    void 윤년_계산() {
        // 2000년은 4로 나누어 떨어지고 100으로 나누어 떨어져도 400으로 나누어 떨어지기 때문에 윤년
        int month[] = {2016, 2000, 1904, 1908, 1912, 1916, 1920, 1924, 1928, 1932, 1936, 1940, 1944};
        boolean isLeap = Arrays.stream(month).allMatch(e -> calendarService.getLeapYear(e));
        assertThat(isLeap).isTrue();


        // 2100은 4로 나누어 떨어져도 100으로 떨어져서 평년
        assertThat(calendarService.getLeapYear(2100)).isFalse();
    }
}