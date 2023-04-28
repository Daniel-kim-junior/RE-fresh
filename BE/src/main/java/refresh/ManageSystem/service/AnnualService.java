package refresh.ManageSystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.model.AnnualCalModel;
import refresh.ManageSystem.repository.AnnualRepository;
import refresh.ManageSystem.vo.AnnualCalVO;

@Service
public class AnnualService {
    @Autowired
    private AnnualRepository annualRepository;

    @Autowired
    private DepartmentService departmentService;

    public List<AnnualCalVO> getAnnualCalData(String year, String month) {
        return annualRepository.getAnnualCalData(AnnualCalModel.
                                                         builder()
                                                         .year(year)
                                                         .month(month)
                                                         .departmentName(String.valueOf(
                                                                 departmentService.getDepartmentNameById("member2")))
                                                         .build());
    }


}
