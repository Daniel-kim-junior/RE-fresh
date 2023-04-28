package refresh.ManageSystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * Calendar 정보를 Client에게 전달해 줄 Response DTO
 * day : 날짜
 * hoName : [평일, 일요일, 공휴일 이름, 빈 문자] 로 이루어져 있으며 빈 문자인 경우 이번 달이 아님
 * sumCount : 연차 집계 데이터
 *
 * 2023-04-22
 */
@Builder @Getter @ToString
public class CalendarServiceDTO {
    private int day;
    @NonNull private String hoName;
    private int sumCount;
}
