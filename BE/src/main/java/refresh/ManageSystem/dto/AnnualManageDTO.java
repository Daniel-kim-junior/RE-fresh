package refresh.ManageSystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
public class AnnualManageDTO {
    private String annualUid;
    private String memberName;
    private String departmentName;
    private String annualType;
    private String annualStatus;
    private String startDate;
    private String endDate;
    private String createTm;
}
