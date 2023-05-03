package refresh.ManageSystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


@Builder
@Getter
@ToString
public class AnnualManageDTO {
    @NonNull
    private String annualUid;
    @NonNull
    private String memberName;
    @NonNull
    private String departmentName;
    @NonNull
    private String annualType;
    @NonNull
    private String annualStatus;
    @NonNull
    private String startDate;
    @NonNull
    private String endDate;
    @NonNull
    private String createTm;
}
