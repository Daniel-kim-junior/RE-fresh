package refresh.ManageSystem.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * 로그인 시 사용할 DTO
 * id : 아이디
 * password : 비밀번호(암호화)
 *
 * 2023-05-01
 */
@Getter @Setter @ToString
public class MemberLoginDTO {
    @NonNull private String id;
    @NonNull private String password;

    @NonNull private String memberName;
    private String authority;
}
