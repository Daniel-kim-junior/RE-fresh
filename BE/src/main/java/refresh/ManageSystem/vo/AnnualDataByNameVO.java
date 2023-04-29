package refresh.ManageSystem.vo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class AnnualDataByNameVO {
    private String memberName;
    private LocalDate startDate;
    private LocalDate endDate;
}
