package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.Getter;

import lombok.NonNull;
import lombok.ToString;

/**
 * Daniel Kim
 *
 * Holiday 테이블에 접근하는 Request DAO
 * year : 연도
 * month : 월
 *
 * 2023-04-18
 */
@Getter @ToString @Builder
public class HolidayDbDAO {
    @NonNull private String year;
    @NonNull private String month;
}
