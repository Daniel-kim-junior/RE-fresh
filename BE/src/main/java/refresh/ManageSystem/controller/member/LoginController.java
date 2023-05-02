package refresh.ManageSystem.controller.member;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.service.MemberService;
import refresh.ManageSystem.util.hash.SHA256;
/**
 * Daniel Kim
 *
 * @Controller : return 을 Thymeleaf로 하는 Controller 설정
 * @SessionAttributes : Model 에 담긴 객체 중 MemberLogin 이라는 이름의 객체를 Session 에 저장하게 설정
 * 전달받은 암호를 SHA-256으로 암호화하여 DB에 저장된 암호화된 암호와 비교하여 로그인을 처리한다.
 *
 * 2023-05-01
 */
@Controller
@SessionAttributes("MemberLogin")
public class LoginController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private SHA256 sha256;

    /**
     * 로그인 페이지
     * @param model
     * @return
     * 멤버 로그인 DTO 를 통해 로그인 정보를 클라이언트로 부터 전달 받는다.
     *
     * 2023-05-01
     */
    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("MemberLogin", new MemberLoginDTO());
        return "/index";
    }

    /**
     * 로그인 요청
     * @param memberLoginDTO
     * @param model
     * @return
     * @throws Exception
     * 멤버 로그인 DTO 를 통해 로그인 정보를 클라이언트로 부터 전달 받는다.
     * 전달 받은 암호를 SHA-256으로 암호화하여 DB에 저장된 암호화된 암호와 비교하여 로그인을 처리한다.
     *
     * 2023-05-01
     */
    @PostMapping
    public String loginRequest(@ModelAttribute MemberLoginDTO memberLoginDTO, Model model) throws Exception {

        String cryptoPassword = sha256.getHash(memberLoginDTO.getPassword(), "SHA-256");
        Optional<String> loginRequest = memberService.login(memberLoginDTO.getId(), cryptoPassword);
        if(loginRequest.isEmpty()) {
            return "redirect:/";
        }
        MemberLoginDTO memberLogin = (MemberLoginDTO) model.getAttribute("MemberLogin");
        memberLogin.setId(memberLoginDTO.getId());
        memberLogin.setPassword(cryptoPassword);
        model.addAttribute("MemberLogin", memberLogin);


        return "/pages/calendar/calendar";
    }

}
