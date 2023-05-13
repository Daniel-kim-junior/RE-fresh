package refresh.ManageSystem.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * Park JuHee
 * 사용자의 연차 사용횟수와 잔여 횟수 DTO
 * totalCount : 총 연차 갯수
 * remainCount : 남은 연차 횟수
 * usageCount : 사용 연차 횟수
 * */

@Getter @ToString
public class AnnualCntDTO {
    private Double totalCount = 15.0;
    private Double remainCount;
    private Double usageCount;

    public AnnualCntDTO(Double remainCount){
        this.remainCount = remainCount;
        this.usageCount = totalCount - remainCount;
    }

}
