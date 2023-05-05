package refresh.ManageSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSearchDTO {
    private String departmentName;
    private String memberName;
    private String memberStatus;
    private PageDTO pageDTO;
}
