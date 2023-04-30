package refresh.ManageSystem.vo;
import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * 필터 검색을 통해 받아온 연차 데이터를 담는 VO
 * name : 사원 이름
 * startDate : 연차 시작 날짜
 * endDate : 연차 종료 날짜
 *
 * 2023-04-29
 */
@Getter @ToString
public class AnnualDataByFilterVO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
