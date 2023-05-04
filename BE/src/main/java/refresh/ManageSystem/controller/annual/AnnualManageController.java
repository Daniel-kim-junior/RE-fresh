package refresh.ManageSystem.controller.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.AnnualManageService;
import refresh.ManageSystem.service.DepartmentService;
import refresh.ManageSystem.vo.AnnualStatusVO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Park JuHee
 * 관리자의 연차관리 페이지 컨트롤러
 * 2023-05-03
 *  * */
@Controller
public class AnnualManageController {

    @Autowired
    private AnnualManageService annualManageService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    HttpSession session;

    //연차관리 조회
    @GetMapping("/admin/annualManage")
    public String getAnnualManage(Model model){
        if(!verifyAdmin()) return "redirect:/";

        List<AnnualManageDTO> annualList = annualManageService.getAnnualManageList();
        model.addAttribute("annualList", annualList);

        List<String> deptNames = departmentService.getDepartmentAllList();
        model.addAttribute("deptNames",deptNames);

        model.addAttribute("searchData", new AnnualSearchDTO());

        return "/pages/admin/annual/annualmanage";
    }

    //연차관리 검색 컨트롤러
    @PostMapping("/admin/annualManage")
    public String postAnnualManage(@ModelAttribute("searchData")AnnualSearchDTO searchDto , Model model ){
        if(!verifyAdmin()) return "redirect:/";

        List<AnnualManageDTO> annualList = annualManageService.getAnnualSearchList(searchDto);
        model.addAttribute("annualList",annualList);

        List<String> deptNames = departmentService.getDepartmentAllList();
        model.addAttribute("deptNames",deptNames);

        model.addAttribute("searchData", new AnnualSearchDTO());
        return "/pages/admin/annual/annualmanage";
    }

    //연차 승인 상태 컨트롤러
    @PutMapping("/admin/status")
    public boolean updateAnnualStatus(@RequestBody AnnualStatusVO status, @SessionAttribute("MemberLogin")MemberLoginDTO memberDTO){
        if(!verifyAdmin()) return false;
        return annualManageService.updateAnnualStatus(status, memberDTO.getId() );
    }

   //session 검증 및 admin검증 메소드
    private boolean verifyAdmin() {
       MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        return (memberLoginDTO != null && memberLoginDTO.getAuthority().equals("admin"))? true :false;
    }
}
