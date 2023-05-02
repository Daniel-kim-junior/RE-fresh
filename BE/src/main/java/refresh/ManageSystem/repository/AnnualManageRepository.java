package refresh.ManageSystem.repository;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.vo.AnnualManageVO;

import java.util.List;

@Mapper
public interface AnnualManageRepository {
    List<AnnualManageVO> getAnnualManageList();
}
