package refresh.ManageSystem.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import refresh.ManageSystem.dao.PageDAO;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.dto.MemberSearchDTO;
import refresh.ManageSystem.dto.MemberServiceDTO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.service.DepartmentService;
import refresh.ManageSystem.service.MemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MemberController {

    @Autowired
    HttpSession session;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/admin/members/new")
    private String createForm(Model model) {
        if(!verifyAdmin()) return "redirect:/";

        model.addAttribute("department", departmentService.getDepartmentAllList());
        model.addAttribute("memberServiceDTO", new MemberServiceDTO());
        return "/pages/admin/member/createMemberForm";
    }

    @PostMapping("/admin/members/new")
    private String create(@Valid MemberServiceDTO member, BindingResult result, Model model) {
        if(!verifyAdmin()) return "redirect:/";

        model.addAttribute("department", departmentService.getDepartmentAllList());
        System.out.println("checkId : "+ memberService.checkId(member));

        if(memberService.checkId(member)) {
            model.addAttribute("idCheckValue", "이미 사용하고 있는 아이디입니다. <br> 다른 아이디를 입력하세요.");
            return "/pages/admin/member/createMemberForm";
        }
        if(result.hasErrors()) {
            return "/pages/admin/member/createMemberForm";
        }

        if(member.getMemberAuth() == null) {
            member.setMemberAuth("member");
        }
        memberService.create(member);
        return "redirect:/admin/members?page=1";
    }

    @GetMapping("/admin/members")
    public String list(Model model, int page) {
        if(!verifyAdmin()) return "redirect:/";

        model.addAttribute("department", departmentService.getDepartmentAllList());

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setStart((page-1) * pageDTO.getPerPageNum());

        PageDAO pageDAO = new PageDAO();
        pageDAO.setPageDTO(pageDTO);
        pageDAO.setTotalCount(memberService.getMemberAllList().size());
        pageDAO.pageMaker();

        model.addAttribute("memberVO", memberService.getMemberAllListByPage(pageDTO));
        model.addAttribute("pageDAO", pageDAO);
        model.addAttribute("currentPage", page);

        model.addAttribute("memberSearchDTO", new MemberSearchDTO());
        return "/pages/admin/member/memberList";

    }

    @GetMapping("/admin/members/search")
    public String search(MemberSearchDTO dto, int page, Model model) {
        if(!verifyAdmin()) return "redirect:/";

        model.addAttribute("department", departmentService.getDepartmentAllList());

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setStart((page-1) * pageDTO.getPerPageNum());
        dto.setPageDTO(pageDTO);

        PageDAO pageDAO = new PageDAO();
        pageDAO.setPageDTO(pageDTO);
        pageDAO.setTotalCount(memberService.countMemberSearchList(dto));
        pageDAO.pageMaker();

        model.addAttribute("memberVO", memberService.getMemberSearchList(dto));
        model.addAttribute("pageDAO", pageDAO);
        model.addAttribute("currentPage", page);

        return "/pages/admin/member/memberList";
    }

    //session 검증 및 admin검증 메소드
    private boolean verifyAdmin() {
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        return (memberLoginDTO != null && memberLoginDTO.getAuthority().equals("admin"))? true :false;
    }
}
