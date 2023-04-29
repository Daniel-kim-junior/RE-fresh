package refresh.ManageSystem.service;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualDataByNameVO;

@SpringBootTest
class AnnualServiceTest {

    @Autowired
    private AnnualService annualService;


    @Test
    void 월_연차_집계_정보_확인() {
        List<AnnualCalVO> annualCalData1 = annualService.getAnnualCalData("2010", "5");
        assertThat(annualCalData1).isEmpty();

        List<AnnualCalVO> annualCalData2 = annualService.getAnnualCalData("2023", "4");
        assertThat(annualCalData2.size()).isEqualTo(5);
    }

    @Test
    void 사원_연차_확인() {
        List<AnnualDataByNameVO> annual1 = annualService.getAnnualDataByName("강감");
        assertThat(annual1.size()).isEqualTo(1);

        List<AnnualDataByNameVO> annual2 = annualService.getAnnualDataByName("박영희");
        assertThat(annual2.size()).isEqualTo(0);

        // 없는 사람
        List<AnnualDataByNameVO> annual3 = annualService.getAnnualDataByName("로버트");
        assertThat(annual3.size()).isEqualTo(0);

        // 빈 문자열
        List<AnnualDataByNameVO> annual4 = annualService.getAnnualDataByName("");
        assertThat(annual3.size()).isEqualTo(0);
    }
}