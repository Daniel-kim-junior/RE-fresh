package refresh.ManageSystem.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString @Getter @Builder
public class AnnualCalModel {
    private String year;
    private String month;
    private String departmentName;
}
