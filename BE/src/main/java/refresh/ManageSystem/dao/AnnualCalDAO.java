package refresh.ManageSystem.dao;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * year: 년도
 * month: 달
 * departmentName: 부서 이름
 *
 * 2023-04-28
 */
@ToString @Getter @Builder
public class AnnualCalDAO {
    @NonNull private String year;
    @NonNull private String month;
    @NonNull private String departmentName;
}
