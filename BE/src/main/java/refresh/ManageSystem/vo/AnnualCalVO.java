package refresh.ManageSystem.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * 연차 집계 데이터를 담는 VO
 *
 * 2023-04-28
 */
@Getter @ToString
public class AnnualCalVO {
    private int day;
    private int sumCount;
}
