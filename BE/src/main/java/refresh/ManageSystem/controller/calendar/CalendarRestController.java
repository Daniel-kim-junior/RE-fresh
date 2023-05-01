package refresh.ManageSystem.controller.calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import refresh.ManageSystem.service.AnnualService;
import refresh.ManageSystem.dto.CalendarServiceDTO;
import refresh.ManageSystem.service.CalendarService;
import refresh.ManageSystem.service.DepartmentService;
import refresh.ManageSystem.vo.AnnualDataByFilterVO;

/**
 * Daniel Kim
 *
 * @CrossOrigin : Webpack Local Server 에서 Rest 로 통신하는 것을 허용해주기 위해 설정했다 모든 Origin 을 허용했는데
 *               후에 특정 Origin 만 허용 가능하게 변경 요망
 * @RestController : return 을 문자열로 하는 Controller 설정
 * @RequestMapping : /Calendar 로 들어오는 요청을 mapping 하게 설정하였다.
 *
 * 2023-04-19
 */
@RestController
@RequestMapping("/calendar")
@CrossOrigin
class CalendarRestController {
    @Autowired
    private CalendarService calendarService;
    /**
     * Daniel Kim
     *
     * @param year : Request Validation 추가 예정(백엔드)
     * @param month
     * @return
     * @throws JsonProcessingException
     *
     * 2023-04-19
     */
    @GetMapping
    List<CalendarServiceDTO> getCalendar(@RequestParam String year, @RequestParam String month)
            throws JsonProcessingException {
        return calendarService.updateCalendar(year, month);
    }

}
