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

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentRestController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/allList")
    List<String> getDepartmentList() {
        return departmentService.getDepartmentAllList();
    }

    @GetMapping
    Map<String, String> getDepartment(@SessionAttribute("MemberLogin") MemberLoginDTO memberLoginDTO) {
        String id = memberLoginDTO.getId();

        Optional<String> departmentNameById = departmentService.getDepartmentNameById(id);
//        if(departmentNameById.isEmpty()) {
//            return "redirect:/";
//        }
        return new HashMap<>() {{
            put("department", departmentNameById.get().toString());
        }};
    }

}
