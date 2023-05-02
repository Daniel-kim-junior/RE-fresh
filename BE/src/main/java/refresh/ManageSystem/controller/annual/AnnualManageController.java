package refresh.ManageSystem.controller.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.service.AnnualManageService;

import java.util.List;

@Controller
public class AnnualManageController {

    @Autowired
    private AnnualManageService annualManageService;
    @GetMapping("/admin/annualManage")
    public String getAnnualManage(Model model){
        List<AnnualManageDTO> list = annualManageService.getAnnualManageList();
        model.addAttribute("list",list);

        return "/pages/admin/annualmanage";
    }
}
