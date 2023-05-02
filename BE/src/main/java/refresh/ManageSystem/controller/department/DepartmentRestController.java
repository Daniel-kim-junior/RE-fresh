package refresh.ManageSystem.controller.department;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
