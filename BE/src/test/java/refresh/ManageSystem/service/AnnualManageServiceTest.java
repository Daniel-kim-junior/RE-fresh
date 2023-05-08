package refresh.ManageSystem.service;

import java.text.SimpleDateFormat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import refresh.ManageSystem.dao.AnnualSumCountDAO;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.repository.AnnualSumCountRepository;

/**
 * Daniel Kim
 *
 * AnnualManageServiceTest : 연차 관리 서비스 테스트 클래스
 *
 * 2023-05-02
 */
@MybatisTest
class AnnualManageServiceTest {

    @Autowired
    private AnnualSumCountRepository annualSumCountRepository;

    @AfterEach
    void setClearDB() {
        annualSumCountRepository.dropTable();
    }



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
     * 연차 신청 집계 업데이트 테스트
     *
     * 2023-05-08
     */
    @Test
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    void 부서별_연차_집계_서비스_테스트() throws Exception {
        java.util.Date date1= new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-05");
        java.util.Date date2= new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-07");
        int updateRows = annualSumCountRepository.setAnnualSumCount(AnnualSumCountDAO.builder()
                                                                              .startDate(date1)
                                                                              .endDate(date2)
                                                                              .departmentName("개발팀")
                                                                                     .build());
        Assertions.assertThat(updateRows).isEqualTo(3);

    }


}