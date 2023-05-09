package refresh.ManageSystem.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.dto.AnnualManageDTO;

/**
 * Daniel Kim
 *
 * AnnualManageServiceTest : 연차 관리 서비스 테스트 클래스
 *
 * 2023-05-02
 */
@SpringBootTest
class AnnualManageServiceTest {

    @Autowired
    private AnnualManageService annualManageService;

    /**
     * Daniel Kim
     *
     * AnnualManageDTO date format test
     *
     * 2023-05-02
     */
    @ParameterizedTest
    @CsvSource({"2021-05-02, 2021-05-02", "2021-05-02, 2021-05-03", "2021-05-02, 2021-05-04"})
    void 연차_신청_날짜_포맷_테스트(String startDate, String endDate) throws Exception {
        AnnualManageDTO annualManageDTO = AnnualManageDTO
                .builder().memberName("").departmentName("").annualStatus("").annualUid("").annualType("").createTm("")
                .startDate(startDate).endDate(endDate).build();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        format.parse(annualManageDTO.getStartDate());
        format.parse(annualManageDTO.getEndDate());
        Assertions.assertThat(annualManageDTO.getStartDate()).isEqualTo(startDate);
    }



    /**
     * Daniel Kim
     *
     * 연차 신청 집계 서비스 테스트
     *
     * 2023-05-02
     */
    @Test
    void 연차_신청_집계_서비스_테스트() throws Exception {

    }

}