package refresh.ManageSystem.repository;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.dao.AnnualSearchDAO;
import refresh.ManageSystem.dao.AnnualStatusDAO;
import refresh.ManageSystem.vo.AnnualManageVO;
import java.util.List;

@Mapper
public interface AnnualRepository {
    List<AnnualManageVO> getAnnualManageList();

    List<AnnualManageVO> getAnnualSearchList(AnnualSearchDAO annualSearchDAO);

    boolean updateAnnualStatus(AnnualStatusDAO statusDAO);
}
