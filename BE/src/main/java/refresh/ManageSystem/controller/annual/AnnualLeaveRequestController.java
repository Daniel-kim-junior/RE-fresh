package refresh.ManageSystem.controller.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import refresh.ManageSystem.dto.LeaveRequestDTO;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.LeaveRequestService;
import refresh.ManageSystem.service.MemberService;
import javax.servlet.http.HttpSession;

@Controller
public class AnnualLeaveRequestController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    HttpSession session;

    @GetMapping("/leaveRequest")
    public String requestSuccess(Model model){
        // 모델 객체를 인자로 받아서 leaveRequest 템플릿을 렌더링함
        // 템플릿을 통해 응답
        model.addAttribute("LeaveRequest", new LeaveRequestDTO());
        // LeaveReqeustDTO 객체를 모델에 추가

        MemberLoginDTO member = (MemberLoginDTO) (session.getAttribute("MemberLogin"));
        // 로그인한 아이디 추출
        if(member == null) {
            return "redirect:/";
        }
        String id = member.getId();
        model.addAttribute("annualCnt", memberService.getAnnualCount(id));

        // model attribute -> html에 타임리프로 데이터 뿌려줌

        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        String memberId = memberLoginDTO.getId();
        model.addAttribute("memberInfoVO", memberService.getMemberInfo(memberId));

        return "/pages/leaveRequest/leaveRequest";
    }


    @PostMapping("/leaveRequest/confirmResult")  // 나는 post할때 이 주소로 받을거야. form에 action을 할 때 action은 어떤 post를 작동시킬꺼니 하는것
    public String leaveDisplayHandle( LeaveRequestDTO leaveRequestDTO){
        MemberLoginDTO member = (MemberLoginDTO) (session.getAttribute("MemberLogin"));
        if(member == null) {
            return "redirect:/";
        }

        String id = member.getId();
        leaveRequestService.insert(leaveRequestDTO,id,member.getMemberName());

        return "redirect:/cal";
    }
}