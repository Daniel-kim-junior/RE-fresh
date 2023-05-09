package refresh.ManageSystem.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Daniel Kim
 *
 * 로그아웃 컨트롤러(세션 종료 시 로그인 페이지로 리다이렉트)
 *
 * 2023-05-07
 */
@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
