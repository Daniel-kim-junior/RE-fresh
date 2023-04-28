package refresh.ManageSystem.repository;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.model.AnnualCalModel;
import refresh.ManageSystem.vo.AnnualCalVO;

/**
 * Daniel Kim
 *
 * getAnnualCalData: AnnualCalModel을 통해 연차 집계 데이터를 가져옴
 *
 * 2023-04-28
 */
@Mapper
public interface AnnualRepository {
     List<AnnualCalVO> getAnnualCalData(AnnualCalModel ac);
}
