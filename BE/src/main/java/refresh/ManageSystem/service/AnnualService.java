package refresh.ManageSystem.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.dao.AnnualCalDAO;
import refresh.ManageSystem.dao.AnnualDataByFilterDAO;
import refresh.ManageSystem.repository.AnnualRepository;
import refresh.ManageSystem.vo.AnnualCalVO;
import refresh.ManageSystem.vo.AnnualDataByFilterVO;

/**
 * Daniel Kim
 *
 * 연차 정보를 다루는 서비스 클래스
 *
 * 2023-04-28
 */
@Service
public class AnnualService {
    @Autowired
    private AnnualRepository annualRepository;
    @Autowired
    private DepartmentService departmentService;

    /**
     * Daniel Kim
     *
     * @param year
     * @param month
     * @return
     * 연, 월과 부서 이름을 통해 연차 집계 데이터를 가져옴
     *
     * 2023-04-27
     */
    public List<AnnualCalVO> getAnnualCalData(String year, String month) {
        return annualRepository.getAnnualCalData(AnnualCalDAO.
                                                         builder()
                                                         .year(year)
                                                         .month(month)
                                                         .departmentName(departmentService.getDepartmentNameById("member2").get())
                                                         .build());
    }

    /**
     * Daniel Kim
     *
     * @param memberName
     * @param start
     * @param end
     * @return
     * 사원 이름과 페이지 범위를 통해 연차 집계 데이터를 가져옴
     *
     * 2023-04-29
     */
    public List<AnnualDataByFilterVO> getAnnualDataByName(String memberName, int start, int end) {
        return annualRepository.getAnnualDataByName(AnnualDataByFilterDAO.
                                                         builder()
                                                         .name(memberName)
                                                         .pageStart(start)
                                                         .pageEnd(end)
                                                         .build());
    }

    /**
     * Daniel Kim
     *
     * @param departmentName
     * @param start
     * @param end
     * @return
     * 부서 이름과 페이지 범위를 통해 연차 집계 데이터를 가져옴
     *
     * 2023-04-29
     */
    public List<AnnualDataByFilterVO> getAnnualDataByDepartment(String departmentName, int start, int end) {
        return annualRepository.getAnnualDataByDepartment(AnnualDataByFilterDAO.
                                                         builder()
                                                         .name(departmentName)
                                                         .pageStart(start).pageEnd(end)
                                                         .build());
    }


}
