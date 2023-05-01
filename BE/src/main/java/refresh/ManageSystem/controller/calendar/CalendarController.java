package refresh.ManageSystem.controller.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Daniel Kim
 *
 * Thymeleaf html 로 연결시켜주는 Controller(Server side rendering)
 *
 * 2023-04-20
 */
@Controller
public class CalendarController {
    @GetMapping("/v1")
    String get() {
        return "/pages/calendar/calendar";
    }
}
