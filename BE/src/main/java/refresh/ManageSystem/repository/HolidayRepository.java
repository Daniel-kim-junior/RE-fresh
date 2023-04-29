package refresh.ManageSystem.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import refresh.ManageSystem.vo.HolidayServiceVO;
import refresh.ManageSystem.dao.HolidayApiDAO;
import refresh.ManageSystem.dao.HolidayDbDAO;

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

    void insertHoliday(HolidayApiDAO holiday);
    List<HolidayServiceVO> findHoliday(HolidayDbDAO holidayDbModel);
    void removeHolidayTable();
}
