package refresh.ManageSystem.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthPageController {

    @GetMapping("/auth")
    public String printAuthPage() {
        return "/auth/auth";
    }
}
