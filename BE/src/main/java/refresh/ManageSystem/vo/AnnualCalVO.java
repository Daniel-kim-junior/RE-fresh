package refresh.ManageSystem.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * 연차 집계 데이터를 담는 VO
 * day : 날짜 데이터
 * sumCount : 해당 날짜의 연차 사용 횟수
 *
 * 2023-04-28
 */
@Getter @ToString
public class AnnualCalVO {
    private int day;
    private int sumCount;
}
