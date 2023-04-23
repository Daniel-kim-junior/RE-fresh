package refresh.ManageSystem.repository;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import refresh.ManageSystem.dto.HolidayServiceDTO;
import refresh.ManageSystem.model.HolidayApiModel;
import refresh.ManageSystem.model.HolidayDbModel;

/**
 * Daniel Kim
 *
 * @Mapper : Mybatis 접근 레포지토리 의존성 추가
 * HolidayMapper 에 정의해야 할 method 정의. (메소드 이름 == id)
 * Create, Delete, Find method
 *
 * 2023-04-18
 */
@Mapper
public interface HolidayRepository {

    void insertHoliday(HolidayApiModel holiday);
    List<HolidayServiceDTO> findHoliday(HolidayDbModel holidayDbModel);
    void removeHolidayTable();
}
