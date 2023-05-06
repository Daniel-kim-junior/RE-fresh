package refresh.ManageSystem.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Park JuHee
 * uid : annualUid
 * Status : 사용자가 선택한 승인여부(승인 : true, 반려 : false)
 */
@Getter @ToString
public class AnnualStatusVO {
    private String uid;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String annualType;


}
