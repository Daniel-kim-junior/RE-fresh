package refresh.ManageSystem.service;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualDataByFilterVO;

/**
 * Daniel Kim
 *
 * 연차 관련 서비스 테스트 클래스
 *
 * 2023-04-28
 */
@SpringBootTest
class AnnualServiceTest {

    @Autowired
    private AnnualService annualService;
    /**
     * Daniel Kim
     *
     * 연차 집계 정보를 가져오는 테스트
     *
     * 2023-04-29
     */
    @Test
    void 월_연차_집계_정보_확인() {
        List<AnnualCalVO> annualCalData1 = annualService.getAnnualCalData("2010", "5");
        assertThat(annualCalData1).isEmpty();

        List<AnnualCalVO> annualCalData2 = annualService.getAnnualCalData("2023", "4");
        assertThat(annualCalData2.size()).isEqualTo(5);
    }

    /**
     * Daniel Kim
     *
     * 사원 이름으로 연차 필터링 정보를 가져오는 테스트
     *
     * 2023-04-29
     */
    @Test
    void 사원_이름_연차_검색() {
        List<AnnualDataByFilterVO> annual1 = annualService.getAnnualDataByName("강감", 0, 10);
        assertThat(annual1.size()).isEqualTo(1);

        List<AnnualDataByFilterVO> annual2 = annualService.getAnnualDataByName("박영희", 0, 10);
        assertThat(annual2.size()).isEqualTo(0);

        // 없는 사람
        List<AnnualDataByFilterVO> annual3 = annualService.getAnnualDataByName("로버트", 0, 10);
        assertThat(annual3.size()).isEqualTo(0);

        // 빈 문자열
        List<AnnualDataByFilterVO> annual4 = annualService.getAnnualDataByName("", 0, 10);
        assertThat(annual3.size()).isEqualTo(0);
    }
    /**
     * Daniel Kim
     *
     * 부서 이름으로 연차 필터링 정보를 가져오는 테스트
     *
     * 2023-04-29
     */
    @Test
    void 부서_이름_연차_검색() {
        List<AnnualDataByFilterVO> annual1 = annualService.getAnnualDataByDepartment("개발팀", 0, 10);
        assertThat(annual1.size()).isEqualTo(3);

        List<AnnualDataByFilterVO> annual2 = annualService.getAnnualDataByDepartment("인사팀", 0, 10);
        assertThat(annual2.size()).isEqualTo(0);
    }

}