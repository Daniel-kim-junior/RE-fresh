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
        Optional<String> member = memberService.login("member2", sha256.getHash("5678", "SHA-256"));
        assertThat(member.isPresent()).isTrue();
    }

    @Test
    void 실패_로그인_테스트() throws Exception {
        Optional<String> member = memberService.login("member3", sha256.getHash("abca", "SHA-256"));
        assertThat(member.isPresent()).isFalse();
    }
}
