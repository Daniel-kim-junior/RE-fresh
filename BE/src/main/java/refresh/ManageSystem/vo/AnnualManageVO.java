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

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date endDate;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTm;
}
