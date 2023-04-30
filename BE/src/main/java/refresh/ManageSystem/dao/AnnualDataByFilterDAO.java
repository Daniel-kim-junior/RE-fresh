package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * name: (부서, 사원) 이름
 * pageStart: 시작 Index
 * pageEnd: 몇 개를 들고 올 것인지
 *
 * 2023-04-29
 */
@Getter @ToString @Builder
public class AnnualDataByFilterDAO {
    @NonNull
    private String name;
    private int pageStart;

    private int pageEnd;
}
