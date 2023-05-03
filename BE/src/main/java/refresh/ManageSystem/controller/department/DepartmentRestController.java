package refresh.ManageSystem.controller.department;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.DepartmentService;

/**
 * Daniel Kim
 *
 * 부서 정보를 가져오는 컨트롤러
 *
 * 2023-05-01
 */
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentRestController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * Daniel Kim
     *
     * @return 모든 부서 정보를 가져옴
     *
     * 2023-05-01
     */
    @GetMapping("/allList")
    List<String> getDepartmentList() {
        return departmentService.getDepartmentAllList();
    }
    /**
     * Daniel Kim
     *
     * @return 세션 정보를 토대로 해당 유저의 부서 정보를 가져옴
     *
     * 2023-05-01
     */
    @GetMapping
    Map<String, String> getDepartment(@SessionAttribute("MemberLogin") MemberLoginDTO memberLoginDTO) {
        String id = memberLoginDTO.getId();

        Optional<String> departmentNameById = departmentService.getDepartmentNameById(id);

        return new HashMap<>() {{
            put("department", departmentNameById.get().toString());
        }};
    }

}
