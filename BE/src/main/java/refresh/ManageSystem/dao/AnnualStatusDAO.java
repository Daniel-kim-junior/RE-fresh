package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;

/**
 * PARk JUHEE
 * uid :client에서 받은 annualUid
 * acceptor : 연차 수락한 관리자명
 * status : client에서 받은 연차 수락여부
 * 2023-05-03
 * */
@Getter @Builder
public class AnnualStatusDAO {
    private String uid;
    private String acceptor;
    private String status;
}
