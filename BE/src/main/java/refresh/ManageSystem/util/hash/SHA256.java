package refresh.ManageSystem.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

/**
 * Daniel Kim
 *
 * SHA256 암호화를 빈으로 등록
 *
 * 2023-04-28
 */
@Component
public class SHA256 {
    public String getHash(String input, String algorithm) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes());
            byte[] digest = md.digest();
            for(byte b : digest) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NoSuchAlgorithmException();
        }
        return sb.toString();
    }
}
