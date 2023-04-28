package refresh.ManageSystem.repository;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import refresh.ManageSystem.model.AnnualCalModel;
import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualMemberVO;

@Mapper
public interface AnnualRepository {
     List<AnnualCalVO> getAnnualCalData(AnnualCalModel ac);
     AnnualMemberVO getAnnualMemberData();
}
