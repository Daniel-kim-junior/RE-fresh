package refresh.ManageSystem.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import refresh.ManageSystem.vo.AnnualServiceVO;

/**
 * Daniel Kim
 *
 * Calendar 정보를 Client에게 전달해 줄 Response DTO
 * day : 날짜
 * hoName : [평일, 일요일, 공휴일 이름, 빈 문자] 로 이루어져 있으며 빈 문자인 경우 이번 달이 아님
 *
 * 2023-04-22
 */
@Builder @Getter @ToString
public class CalendarServiceDTO {
    private int day;
    @NonNull private String hoName;
    private List<AnnualServiceVO> annualList;

    private int sumCount;
}
