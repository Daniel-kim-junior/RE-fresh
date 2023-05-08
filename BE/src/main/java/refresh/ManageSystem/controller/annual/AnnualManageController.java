package refresh.ManageSystem.controller.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import refresh.ManageSystem.dao.PageDAO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.service.AnnualManageService;
import refresh.ManageSystem.service.DepartmentService;
import refresh.ManageSystem.service.MemberService;
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
    @Autowired
    MemberService memberService;

    @GetMapping("/admin/annualManage")
    public String getAnnualManage(Model model, int page){

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setStart((page-1) * pageDTO.getPerPageNum());

        PageDAO pageDAO = new PageDAO();
        pageDAO.setPageDTO(pageDTO);
        pageDAO.setTotalCount(annualManageService.getAnnualManageList().size());
        pageDAO.pageMaker();

        model.addAttribute("annualList", annualManageService.getAnnualManageListByPage(pageDTO));
        model.addAttribute("pageDAO", pageDAO);
        model.addAttribute("currentPage", page);

        List<String> deptNames = departmentService.getDepartmentAllList();
        model.addAttribute("deptNames",deptNames);

        model.addAttribute("searchData", new AnnualSearchDTO());

        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        String memberId = memberLoginDTO.getId();
        model.addAttribute("memberInfoVO", memberService.getMemberInfo(memberId));

        return "/pages/admin/annual/annualmanage";
    }

    @GetMapping("/admin/annualManage/search")
    public String seach(AnnualSearchDTO dto, int page, Model model) {

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setStart((page-1) * pageDTO.getPerPageNum());
        dto.setPageDTO(pageDTO);

        PageDAO pageDAO = new PageDAO();
        pageDAO.setPageDTO(pageDTO);
        pageDAO.setTotalCount(annualManageService.countAnnualSearchList(dto));
        pageDAO.pageMaker();

        model.addAttribute("annualList", annualManageService.getAnnualManageSearchList(dto));
        model.addAttribute("pageDAO", pageDAO);
        model.addAttribute("currentPage", page);

        List<String> deptNames = departmentService.getDepartmentAllList();
        model.addAttribute("deptNames",deptNames);

        model.addAttribute("searchData", new AnnualSearchDTO());

        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        String memberId = memberLoginDTO.getId();
        model.addAttribute("memberInfoVO", memberService.getMemberInfo(memberId));

        return "/pages/admin/annual/annualmanage";
    }

    //연차 승인 상태 컨트롤러
    @PutMapping("/admin/status")
    @ResponseBody
    public boolean updateAnnualStatus(@RequestBody AnnualStatusVO status, @SessionAttribute("MemberLogin")MemberLoginDTO memberDTO){

        if(!verifyAdmin()) return false;
        return annualManageService.updateAnnualStatus(status, memberDTO.getId());
    }

   //session 검증 및 admin검증 메소드
//    @ResponseBody
    private boolean verifyAdmin() {
       MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        return (memberLoginDTO != null && memberLoginDTO.getAuthority().equals("admin"))? true :false;
    }
    
}
