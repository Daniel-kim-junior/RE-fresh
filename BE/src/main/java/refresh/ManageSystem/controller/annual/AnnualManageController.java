package refresh.ManageSystem.controller.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.service.AnnualManageService;
import refresh.ManageSystem.service.DepartmentService;
import refresh.ManageSystem.vo.AnnualStatusVO;
import java.util.List;

@Controller
public class AnnualManageController {

    @Autowired
    private AnnualManageService annualManageService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/admin/annualManage")
    public String getAnnualManage(Model model){
        List<AnnualManageDTO> annualList = annualManageService.getAnnualManageList();
        model.addAttribute("annualList",annualList);

        List<String> deptNames = departmentService.getDepartmentAllList();
        model.addAttribute("deptNames",deptNames);

        model.addAttribute("searchData", new AnnualSearchDTO());

        return "/pages/admin/annual/annualmanage";
    }

    @PostMapping("/admin/annualManage")
    public String postAnnualManage(@ModelAttribute("searchData")AnnualSearchDTO searchDto , Model model ){
        List<AnnualManageDTO> annualList = annualManageService.getAnnualSearchList(searchDto);
        model.addAttribute("annualList",annualList);

        List<String> deptNames = departmentService.getDepartmentAllList();
        model.addAttribute("deptNames",deptNames);

        model.addAttribute("searchData", new AnnualSearchDTO());
        return "/pages/admin/annual/annualmanage";
    }

    @PutMapping("/admin/status")
    public boolean updateAnnualStatus(@RequestBody AnnualStatusVO status ){
        return annualManageService.updateAnnualStatus(status);
    }
}
