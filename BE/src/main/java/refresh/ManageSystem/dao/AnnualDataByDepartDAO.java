package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter @ToString @Builder
public class AnnualDataByDepartDAO {
    @NonNull private String departmentName;
    private int pageStart;

    private int pageEnd;
}
