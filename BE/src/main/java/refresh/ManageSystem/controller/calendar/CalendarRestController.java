package refresh.ManageSystem.controller.calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;

import refresh.ManageSystem.dto.MemberLoginDTO;
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
    @Autowired
    private DepartmentService departmentService;

    /**
     * Daniel Kim
     *
     * @param year : 연도
     * @param month : 월
     * @param memberLogin : 로그인 정보
     * @return
     * @throws JsonProcessingException
     *
     * 2023-04-19
     */
    @GetMapping
    List<CalendarServiceDTO> getCalendar(@RequestParam String year, @RequestParam String month, @SessionAttribute("MemberLogin") MemberLoginDTO memberLogin)
            throws JsonProcessingException {
        Optional<String> departmentName = departmentService.getDepartmentNameById(memberLogin.getId());
        return calendarService.updateCalendar(year, month, departmentName.get());
    }

    /**
     * Daniel Kim
     *
     * @param year : 연도
     * @param month : 월
     * @param departmentName : 부서명
     * @return
     * @throws JsonProcessingException
     *
     * 2023-04-19
     */

    @GetMapping("/department")
    List<CalendarServiceDTO> getCalendarByDepartment(@RequestParam String year, @RequestParam String month, @RequestParam String departmentName)
            throws JsonProcessingException {
        return calendarService.updateCalendar(year, month, departmentName);
    }

}
