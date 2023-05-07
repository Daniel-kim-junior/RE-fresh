package refresh.ManageSystem.vo;

import lombok.Getter;

/**
 * mowgood
 *
 * 회원 프로필 정보를 담는 VO
 * departmentName : 부서명
 * annualCount : 잔여 연차 수
 * annualRequest : 연차 신청 수
 */

@Getter
public class MemberInfoVO {
    private String departmentName;
    private double annualCount;
    private int annualRequest;
}
