package refresh.ManageSystem.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.dao.AnnualCalDAO;
import refresh.ManageSystem.dao.AnnualDataByDepartDAO;
import refresh.ManageSystem.dao.AnnualDataByNameDAO;
import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualDataByFilterVO;

/**
 * Daniel Kim
 *
 * getAnnualCalData: AnnualCalModel을 통해 연차 집계 데이터를 가져옴
 *
 * 2023-04-28
 */
@Mapper
public interface AnnualRepository {
     List<AnnualCalVO> getAnnualCalData(AnnualCalDAO dao);

    List<AnnualDataByFilterVO> getAnnualDataByName(AnnualDataByNameDAO dao);

    List<AnnualDataByFilterVO> getAnnualDataByDepartment(AnnualDataByDepartDAO dao);
}
