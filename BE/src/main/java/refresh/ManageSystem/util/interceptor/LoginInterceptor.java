package refresh.ManageSystem.util.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import refresh.ManageSystem.dto.MemberLoginDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));

        if(memberLoginDTO == null || memberLoginDTO.getId() == null) {
            response.sendRedirect("/auth");
            return false;
        }  else {
            return true;
        }
    }
}
