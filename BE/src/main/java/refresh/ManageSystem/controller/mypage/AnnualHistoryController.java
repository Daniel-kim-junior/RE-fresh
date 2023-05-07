package refresh.ManageSystem.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.AnnualManageService;
import refresh.ManageSystem.service.MemberService;
import refresh.ManageSystem.vo.AnnualStatusVO;

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
        if(!isVerify()) return "redirect:/";
        else {
            String id = member.getId();
            model.addAttribute("history", annualManageService.getAnnualHistoryList(id));
            model.addAttribute("userName", member.getMemberName());
            model.addAttribute("annualCnt", memberService.getAnnualCount(id));

            MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
            String memberId = memberLoginDTO.getId();
            model.addAttribute("memberInfoVO", memberService.getMemberInfo(memberId));

            return "/pages/mypage/history/annualHistory";
        }
    }

    @PutMapping("/mypage/cancel")
    @ResponseBody
    public boolean cancleAnnualHistory(@RequestBody AnnualStatusVO status){
        if(!isVerify()) return false;
        return annualManageService.cancelAnnualRequest(status);
    }

    private boolean isVerify(){
        return (session.getAttribute("MemberLogin") != null) ? true : false;
    }
}
