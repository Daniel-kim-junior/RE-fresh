package refresh.ManageSystem.controller.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ErrorPageController implements ErrorController {

    @GetMapping("/error")
    public String printErrorPage(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.debug("status : {}", status);
        if(status != null) {
            return "/error/errorPage";
        }
        return "/error/errorPage";
    }
}
