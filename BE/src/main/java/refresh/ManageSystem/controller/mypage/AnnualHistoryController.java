package refresh.ManageSystem.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnnualHistoryController {

    @GetMapping("/mypage/history")
    public String getAnnualHistory(){

        return "/pages/mypage/history/annualHistory";
    }
}
