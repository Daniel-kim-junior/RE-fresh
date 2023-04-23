package refresh.ManageSystem.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
/**
 * Daniel Kim
 *
 * Holiday Table 에서 데이터를 정제해서 받아올 DTO
 * day : 날짜 데이터
 * dateName : 공휴일 이름
 *
 * 2023-04-22
 */
@Getter @ToString
public class HolidayServiceDTO {
    private int day;
    @NonNull private String dateName;

}
