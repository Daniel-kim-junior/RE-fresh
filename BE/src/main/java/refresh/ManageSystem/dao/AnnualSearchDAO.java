package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import refresh.ManageSystem.dto.PageDTO;

@ToString
@Getter
@Builder
public class AnnualSearchDAO {
    private String departmentName;
    private String memberName;
    private String startDate;
    private String endDate;
}
