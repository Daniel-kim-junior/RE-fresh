package refresh.ManageSystem.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*
* @NotNull : startDate, endDate가 null인 경우 검증하지 않도록 함
* @AssertTrue : 반환결과가 false일 경우 설정한 에러 메시지 출력
*
* */

@Getter
@Setter
public class LeaveRequestDTO {

    private String leaveType;  // 휴가 종류

    @NotNull(message = "시작일을 입력하세요")
    private String startDate;  // 휴가 시작 날짜

    @NotNull(message = "종료일을 입력하세요")
    private String endDate;   // 휴가 종료 날짜

}