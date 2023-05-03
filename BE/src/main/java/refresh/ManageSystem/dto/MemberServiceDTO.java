package refresh.ManageSystem.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter @Setter
public class MemberServiceDTO {

    @NotNull(message = "부서명을 선택하세요")
    private String departmentName;

    @NotBlank(message = "사원번호를 입력하세요.")
    @Size(min = 3, max = 10, message = "사원번호를 3자 이상 10자 이하로 입력하세요")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, max = 20, message = "비밀번호를 4자 이상 20자 이하로 입력하세요")
    private String memberPassword;

    @NotBlank(message = "이름을 입력하세요")
    private String memberName;

    @NotBlank(message = "전화번호를 입력하세요")
    @Pattern(regexp = "(01[016789])-(\\d{3,4})-(\\d{4})", message = "전화번호 형식을 확인하세요.")
    private String memberCellphone;

    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식을 확인하세요.")
    private String memberEmail;

    private String memberAuth;

    private String createId;

    private String updateId;
}
