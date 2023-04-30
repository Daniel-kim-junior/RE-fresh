package refresh.ManageSystem.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.dao.AnnualCalDAO;
import refresh.ManageSystem.dao.AnnualDataByFilterDAO;
import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualDataByFilterVO;

/**
 * Daniel Kim
 *
 * getAnnualCalData: AnnualCalModel을 통해 연차 집계 데이터를 가져옴
 * getAnnualDataByName: AnnualDataByFilterModel을 통해 사원 이름으로 연차 데이터를 가져옴
 * getAnnualDataByDepartment: AnnualDataByFilterModel을 통해 부서 이름으로 연차 데이터를 가져옴
 *
 * 2023-04-28
 */
@Mapper
public interface AnnualRepository {
     List<AnnualCalVO> getAnnualCalData(AnnualCalDAO dao);

    List<AnnualDataByFilterVO> getAnnualDataByName(AnnualDataByFilterDAO dao);

    List<AnnualDataByFilterVO> getAnnualDataByDepartment(AnnualDataByFilterDAO dao);
}
