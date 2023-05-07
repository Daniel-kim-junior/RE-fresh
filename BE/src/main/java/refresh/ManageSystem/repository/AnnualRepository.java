package refresh.ManageSystem.repository;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.dao.AnnualSearchDAO;
import refresh.ManageSystem.dao.AnnualStatusDAO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.vo.AnnualHistoryVO;
import refresh.ManageSystem.vo.AnnualManageVO;
import java.util.List;

@Mapper
public interface AnnualRepository {
    List<AnnualManageVO> getAnnualManageList();

    List<AnnualManageVO> getAnnualSearchList(AnnualSearchDTO annualSearchDTO);

    List<AnnualHistoryVO> getAnnualByMemberId(String id);

    boolean updateAnnualStatus(AnnualStatusDAO statusDAO);

    List<AnnualManageVO> getAnnualManageListByPage(PageDTO dto);

    int countAnnualSearchList(AnnualSearchDTO dto);

}
