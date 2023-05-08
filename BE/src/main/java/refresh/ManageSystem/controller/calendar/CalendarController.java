package refresh.ManageSystem.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.MemberService;

import javax.servlet.http.HttpSession;

/**
 * Daniel Kim
 *
 * calendar 페이지로 로그인 시 이동하기 위한 컨트롤러
 *
 * 2023-05-08
 */
@Controller
public class CalendarController {

    @Autowired
    HttpSession session;

    @Autowired
    private MemberService memberService;

    @GetMapping("/cal")
    public String get(Model model) {
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));
        String memberId = memberLoginDTO.getId();
        model.addAttribute("memberInfoVO", memberService.getMemberInfo(memberId));

        return "/pages/calendar/calendar";
    }
}
