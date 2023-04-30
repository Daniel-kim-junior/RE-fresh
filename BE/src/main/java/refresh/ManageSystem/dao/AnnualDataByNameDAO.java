package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString @Getter @Builder
public class AnnualDataByNameDAO {
    @NonNull private String memberName;
    private int pageStart;

    private int pageEnd;
}
