package refresh.ManageSystem.dao;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class AnnualSumCountDAO {
    private Date startDate;
    private Date endDate;

    private String departmentName;

}
