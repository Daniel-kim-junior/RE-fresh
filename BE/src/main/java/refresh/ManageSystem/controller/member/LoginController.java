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

@Controller
@SessionAttributes("MemberLogin")
public class LoginController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private SHA256 sha256;

    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("MemberLogin", new MemberLoginDTO());
        return "/index";
    }


    @PostMapping
    public String loginRequest(@ModelAttribute MemberLoginDTO memberLoginDTO, Model model) throws Exception {

        String cryptoPassword = sha256.getHash(memberLoginDTO.getPassword(), "SHA-256");
        Optional<String> loginRequest = memberService.login(memberLoginDTO.getId(), cryptoPassword);
        if(loginRequest.isEmpty()) {
            return "redirect:/";
        }
        MemberLoginDTO memberLogin = new MemberLoginDTO();
        memberLogin.setId(memberLoginDTO.getId());
        memberLogin.setPassword(cryptoPassword);
        model.addAttribute("MemberLogin", memberLogin);


        return "/pages/calendar/calendar";
    }

}
