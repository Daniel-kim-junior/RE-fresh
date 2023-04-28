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
    void 월_연차_집계_정보() {
        List<AnnualCalVO> annualCalData = annualService.getAnnualCalData("2023", "04");
        for(AnnualCalVO an: annualCalData) {
            System.out.println(an);
        }
//        assertThat(annualCalData.size()).isEqualTo(31);

    }
}