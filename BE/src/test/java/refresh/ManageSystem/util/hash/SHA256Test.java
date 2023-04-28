package refresh.ManageSystem.util.hash;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.security.NoSuchAlgorithmException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * Daniel Kim
 *
 * 암호화 SHA-256을 사용하여 DB에 비밀번호 값을 단방향 해시값을 저장
 *
 * 2023-04-25
 */
class SHA256Test {

    @Test
    public void 암호화_검사() throws Exception {
        // given
        SHA256 sha256 = new SHA256();
        String password1 = "1233445";
        // when
        String hash = sha256.getHash(password1, "SHA-256");
        // then
        Assertions.assertThat(hash).isEqualTo(sha256.getHash(password1, "SHA-256"));

        // when & then (올바르지 않은 알고리즘이 입력값으로 들어올 경우)
        assertThrows(NoSuchAlgorithmException.class, () ->
             sha256.getHash(password1, "SHQ-256")
        );

//        String password2 = "";
//        // when & then (빈 입력값이 들어오는 경우)
//        assertThrows(NoSuchElementException.class, () ->
//            sha256.getHash(password2, "SHA-256")
//        );
    }
}