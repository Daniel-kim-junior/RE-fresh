package refresh.ManageSystem.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import refresh.ManageSystem.dao.AnnualCountDAO;
import refresh.ManageSystem.dao.AnnualStatusDAO;
import refresh.ManageSystem.dao.AnnualSumCountDAO;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.repository.AnnualRepository;
import refresh.ManageSystem.repository.AnnualSumCountRepository;
import refresh.ManageSystem.repository.MemberRepository;
import refresh.ManageSystem.vo.AnnualManageVO;

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
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AnnualRepository annualRepository;
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
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
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

    /**
     * Daniel Kim
     *
     * 멤버 연차 부서 uuid를 클라이언트에서 만들 수 없기때문에
     * 쿼리 실행 여부 테스트
     * @throws Exception
     *
     * 2023-05-08
     */
    @Test
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
    void 멤버_연차_개수_서비스_테스트() throws Exception {
        int updateRows = memberRepository.updateAnnulCount(AnnualCountDAO.builder()
                                                                   .count(3.0)
                                                                         .build());
        Assertions.assertThat(updateRows).isEqualTo(0);
    }

    /**
     * Daniel Kim
     *
     * 연차 상태 업데이트 테스트
     * User uuid 를 알 수 없기 때문에 쿼리 실행 여부 테스트
     * @throws Exception
     *
     * 2023-05-08
     */
    @Test
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
    void 연차_상태_업데이트_서비스_테스트() throws Exception {
        int updateRows = annualRepository.updateAnnualStatus(AnnualStatusDAO.builder()
                                                                     .acceptor("admin")
                                                                     .status("승인")
                                                                         .build());
        Assertions.assertThat(updateRows).isEqualTo(0);
    }
    /**
     * Daniel Kim
     *
     * 연차 상태 관리 리스트 페이지 Get 테스트
     * User uuid 를 알 수 없기 때문에 쿼리 실행 여부 테스트
     * @throws Exception
     *
     * 2023-05-08
     */
    @Test
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
    void 연차_상태_관리_리스트_페이지_서비스_테스트() throws Exception {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPerPageNum(10);
        pageDTO.setPage(1);
        pageDTO.setStart(0);
        List<AnnualManageVO> getRows = annualRepository.getAnnualManageListByPage(pageDTO);
        Assertions.assertThat(getRows.size()).isGreaterThan(0);
    }
    /**
     * Daniel Kim
     *
     * 멤버가 가진 연차 개수 조회 테스트
     * @throws Exception
     *
     * 2023-05-08
     */
    @Test
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
    void 연차_개수_조회_서비스_테스트() throws Exception {
        AnnualSearchDTO annualSearchDTO = new AnnualSearchDTO();
        annualSearchDTO.setMemberName("박영희");
        annualSearchDTO.setDepartmentName("개발팀");

        int count = annualRepository.countAnnualSearchList(annualSearchDTO);
        Assertions.assertThat(count).isEqualTo(3);
    }

    @Test
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
    void 멤버_연차_리스트_서비스_테스트() throws Exception {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPerPageNum(10);
        pageDTO.setPage(1);
        pageDTO.setStart(0);
        AnnualSearchDTO annualSearchDTO = new AnnualSearchDTO();
        annualSearchDTO.setMemberName("박영희");
        annualSearchDTO.setDepartmentName("개발팀");
        annualSearchDTO.setPageDTO(pageDTO);

        List<AnnualManageVO> annualSearchList = annualRepository.getAnnualSearchList(annualSearchDTO);
        Assertions.assertThat(annualSearchList.size()).isEqualTo(3);
    }
}