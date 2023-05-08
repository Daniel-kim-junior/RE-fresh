package refresh.ManageSystem.service;
import static org.assertj.core.api.Assertions.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import refresh.ManageSystem.util.hash.SHA256;

/**
 * Daniel Kim
 *
 * LoginServiceTest : 로그인 서비스 테스트 클래스
 *
 * 2023-05-01
 */
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private SHA256 sha256;

    @Autowired
    private MemberService memberService;

    @Test
    void 성공_로그인_테스트() throws Exception {
        Optional<String> member = memberService.login("admin", sha256.getHash("1234", "SHA-256"));
        assertThat(member.isPresent()).isTrue();
    }

    @Test
    void 실패_로그인_테스트() throws Exception {
        Optional<String> member = memberService.login("qweit", sha256.getHash("abca", "SHA-256"));
        assertThat(member.isPresent()).isFalse();
    }

    @Test
    void 권한_정보_테스트() throws Exception {
        String authority = memberService.getAuthority("admin", sha256.getHash("1234", "SHA-256"));
        assertThat(authority).isEqualTo("admin");
    }

    @Test
    void 멤버_이름_정보_테스트() throws Exception {
        String memberName = memberService.getMemberName("admin", sha256.getHash("1234", "SHA-256"));
        assertThat(memberName).isEqualTo("박영희");
    }
}
