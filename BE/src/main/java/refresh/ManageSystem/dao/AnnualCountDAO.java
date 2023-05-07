package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Park JuHee
 * uid : 변경될 연차 uid
 * count :연차 승인시 사용자의 연차 차감 횟수 DAO
 *
 * 2023-05-06
 * */

@Getter @Builder @ToString
public class AnnualCountDAO {
    private String annualUid;
    private Double count;
}
