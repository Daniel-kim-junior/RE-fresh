package refresh.ManageSystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.dao.AnnualCalDAO;
import refresh.ManageSystem.repository.AnnualRepository;
import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualDataByNameVO;

/**
 * Daniel Kim
 *
 * getAnnualCalData: AnnualCalModel을 통해 연차 집계 데이터를 가져옴(연, 월)
 * 현재는 memeber2 고정 유저의 부서 연차 집계 데이터만 가져온다.
 *
 * 2023-04-28
 */
@Service
public class AnnualService {
    @Autowired
    private AnnualRepository annualRepository;
    @Autowired
    private DepartmentService departmentService;

    public List<AnnualCalVO> getAnnualCalData(String year, String month) {
        return annualRepository.getAnnualCalData(AnnualCalDAO.
                                                         builder()
                                                         .year(year)
                                                         .month(month)
                                                         .departmentName(departmentService.getDepartmentNameById("member2").get())
                                                         .build());
    }
    public List<AnnualDataByNameVO> getAnnualDataByName(String name) {
        return annualRepository.getAnnualDataByName(name);
    }


}
