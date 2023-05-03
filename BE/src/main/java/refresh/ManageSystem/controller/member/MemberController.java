package refresh.ManageSystem.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import refresh.ManageSystem.dto.MemberServiceDTO;
import refresh.ManageSystem.service.DepartmentService;
import refresh.ManageSystem.service.MemberService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/admin/members/new")
    private String createForm(Model model) {
        model.addAttribute("department", departmentService.getDepartmentName());
        model.addAttribute("memberServiceDTO", new MemberServiceDTO());
        return "/pages/admin/member/createMemberForm";
    }

    @PostMapping("/admin/members/new")
    private String create(@Valid MemberServiceDTO member, BindingResult result, Model model) {
        model.addAttribute("department", departmentService.getDepartmentName());
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
        return "redirect:/admin/members";
    }

    @GetMapping("/admin/members")
    public String list(Model model) {
        List<String> members = new ArrayList<>();
        model.addAttribute("members", members);
        return "/pages/admin/member/memberList";
    }
}
