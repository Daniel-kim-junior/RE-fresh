package refresh.ManageSystem.service;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import refresh.ManageSystem.dao.LeaveRequestDAO;
import refresh.ManageSystem.repository.LeaveRequestRepository;

/**
 * Daniel Kim
 *
 * 연차 신청 서비스 테스트
 * uuid 를 알 수 없어서 쿼리 테스트
 *
 * 2021-05-09
 */
@MybatisTest
public class LeaveRequestRepositoryTest {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Test
    @Transactional
    @Sql("classpath:db/MakeTable.sql")
    @Sql("classpath:db/MakeDepartment.sql")
    @Sql("classpath:db/MakeMember.sql")
    @Sql("classpath:db/MakeAnnual.sql")
    @Sql("classpath:db/MakeAnnualCount.sql")
    @Rollback
    void 연차_신청_insert_서비스_테스트() throws Exception {
        int insertRows = leaveRequestRepository.insertAnnual(LeaveRequestDAO.builder()
                                                                   .name("박영희")
                                                                   .leaveType("연차")
                                                                   .acceptor("super")
                                                                   .startDate("2023-05-05")
                                                                   .endDate("2023-05-07")
                                                                   .build());
        assertThat(insertRows).isEqualTo(1);

    }


}
