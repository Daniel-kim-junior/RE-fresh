package refresh.ManageSystem.dao;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LeaveRequestDAO {
    private String name; // 신청인
    private String leaveType;  // 휴가 종류
    private String startDate;  // 휴가 시작 날짜
    private String endDate;   // 휴가 종료 날짜
    private String acceptor;  // 휴가 승인권자
}