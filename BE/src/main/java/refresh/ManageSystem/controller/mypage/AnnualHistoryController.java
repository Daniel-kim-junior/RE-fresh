package refresh.ManageSystem.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import refresh.ManageSystem.dto.AnnualCntDTO;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.AnnualManageService;
import refresh.ManageSystem.service.MemberService;

import javax.servlet.http.HttpSession;

@Controller
public class AnnualHistoryController {

    @Autowired
    private AnnualManageService annualManageService;
    @Autowired
    private MemberService memberService;
    @Autowired
    HttpSession session;

    @GetMapping("/mypage/history")
    public String getAnnualHistory(Model model){
        MemberLoginDTO member = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        if(member == null) return "redirect:/";
        else {
            String id = member.getId();
            model.addAttribute("history", annualManageService.getAnnualHistoryList(id));
            model.addAttribute("userName", member.getMemberName());
            model.addAttribute("annualCnt", memberService.getAnnualCount(id));

            return "/pages/mypage/history/annualHistory";
        }
    }


}
