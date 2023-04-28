package refresh.ManageSystem.service;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.vo.AnnualCalVO;

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
}