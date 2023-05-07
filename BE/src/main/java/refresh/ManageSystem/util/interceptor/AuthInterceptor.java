package refresh.ManageSystem.util.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import refresh.ManageSystem.dto.MemberLoginDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberLoginDTO memberLoginDTO = (MemberLoginDTO)(session.getAttribute("MemberLogin"));

        System.out.println("admin 인터셉터");
        System.out.println(memberLoginDTO);

        if(memberLoginDTO == null || memberLoginDTO.getId() == null) {
            response.sendRedirect("/auth");
            return false;
        } else if(!memberLoginDTO.getAuthority().equals("admin")) {
            response.sendRedirect("/auth");
            return false;
        } else {
            return true;
        }
    }

}
