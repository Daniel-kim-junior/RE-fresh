package refresh.ManageSystem.dto;

import lombok.*;

import java.util.Date;
/**
 * Park JuHee
 * Annual Manage의 검색 조건을 Client로 부터 담을 DTO
 */

@Getter
@Setter
@ToString
public class AnnualSearchDTO {
    private String departmentName;
    private String memberName;
    private String startDate;
    private String endDate;
    private PageDTO pageDTO;
}
