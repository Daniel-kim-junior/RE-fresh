package refresh.ManageSystem.controller.annual;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import refresh.ManageSystem.service.AnnualService;
import refresh.ManageSystem.vo.AnnualDataByFilterVO;

@RestController
@RequestMapping("/annual")
@CrossOrigin
public class AnnualRestController {
    @Autowired
    private AnnualService annualService;
    /**
     * Daniel Kim
     *
     * @param name : 사원 이름을 통해 연차 정보 가져오기
     * @param start : 페이지 시작
     * @param end : 페이지 끝
     * @return
     *
     * 2023-04-29
     */
    @GetMapping("/member")
    List<AnnualDataByFilterVO> getAnnualCalendarByMemberName(@RequestParam String name, @RequestParam int start, @RequestParam int end) {
        return annualService.getAnnualDataByName(name, start, end);
    }


    // 부서 이름을 통해 연차 정보 가져오기
    @GetMapping("/department")
    List<AnnualDataByFilterVO> getAnnualCalendarByDepartment(@RequestParam String name, @RequestParam int start,@RequestParam int end) {
        return annualService.getAnnualDataByDepartment(name, start, end);
    }



}
