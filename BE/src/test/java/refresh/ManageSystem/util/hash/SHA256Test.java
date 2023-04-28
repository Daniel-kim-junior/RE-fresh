package refresh.ManageSystem.util.hash;
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
        String password = "1233445";
        // when
        String hash = sha256.getHash(password);
        // then
        Assertions.assertThat(hash).isEqualTo(sha256.getHash(password));
    }
}