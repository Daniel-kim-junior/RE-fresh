package refresh.ManageSystem.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Park JuHee
 * 사용자의 연차 신청 내역 조회 DTO
 * 2023-05-05
 * */

@Getter @ToString
public class AnnualHistoryVO {
    private String annualUid;
    private String annualType;
    private String acceptor;
    private String annualStatus;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date endDate;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date createTm;
}
