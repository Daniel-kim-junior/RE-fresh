package refresh.ManageSystem.dto;

import lombok.Getter;

@Getter
public class MemberDTO {
    private String memberId;
    private String memberName;
    private String departmentId;
    private String departmentName;
    private String memberCellphone;
    private String memberEmail;
    private String createTime;
    private String retireDate;
    private String memberStatus;
}
