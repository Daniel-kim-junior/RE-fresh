package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * Holiday 테이블에 접근하는 API DAO
 * locDate : 년 월 데이터
 * dateName : 공휴일 이름
 *
 * 2023-04-18
 */
@ToString @Getter @Builder
public class HolidayApiDAO {
    @NonNull private String locDate;
    @NonNull private String dateName;


}