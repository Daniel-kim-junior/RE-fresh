package refresh.ManageSystem.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @ToString
public class AnnualManageVO {
    private String annualUid;
    private String memberName;
    private String departmentName;
    private String annualType;
    private String annualStatus;

    private String startDate;
    private String endDate;
    private String createTm;
}
