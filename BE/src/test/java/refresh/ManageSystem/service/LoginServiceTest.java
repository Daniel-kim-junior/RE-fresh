package refresh.ManageSystem.service;
import static org.assertj.core.api.Assertions.*;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import refresh.ManageSystem.util.hash.SHA256;

/**
 * Daniel Kim
 *
 * LoginServiceTest : 로그인 서비스 테스트 클래스
 *
 * 2023-05-01
 */
@SpringBootTest
@Sql("classpath:db/MakeTable.sql")
@Sql("classpath:db/MakeDepartment.sql")
@Sql("classpath:db/MakeMember.sql")
@Sql("classpath:db/MakeAnnual.sql")
@Sql("classpath:db/MakeAnnualCount.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class LoginServiceTest {

    @Autowired
    private SHA256 sha256;
    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    @Rollback
    void 성공_로그인_테스트() throws Exception {
        Optional<String> member = memberService.login("admin", "1234");
        assertThat(member.isPresent()).isTrue();
    }
    @Test
    @Transactional
    @Rollback
    void 실패_로그인_테스트() throws Exception {
        Optional<String> member = memberService.login("qweit", sha256.getHash("abca", "SHA-256"));
        assertThat(member.isPresent()).isFalse();
    }

    @Test
    @Transactional
    @Rollback
    void 권한_정보_테스트() throws Exception {
        String authority = memberService.getAuthority("admin", "1234");
        assertThat(authority).isEqualTo("admin");
    }

    @Test
    @Transactional
    @Rollback
    void 멤버_이름_정보_테스트() throws Exception {
        String memberName = memberService.getMemberName("admin", "1234");
        assertThat(memberName).isEqualTo("박영희");
    }
}
