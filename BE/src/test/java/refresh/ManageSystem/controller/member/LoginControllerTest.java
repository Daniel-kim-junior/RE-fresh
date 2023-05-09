package refresh.ManageSystem.controller.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.util.hash.SHA256;

/**
 * Daniel Kim
 *
 * @Controller : return 을 Thymeleaf로 하는 Controller 테스트
 *
 * 2023-05-08
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private SHA256 sha256;
    private MemberLoginDTO loginDTO;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpSession mockHttpSession;
    @BeforeEach
    void 로그인_정보_만들기() throws Exception {
        loginDTO = new MemberLoginDTO();
        sha256 = new SHA256();
        loginDTO.setId("admin");
        loginDTO.setPassword(sha256.getHash("1234", "SHA-256"));
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("MemberLogin", loginDTO);
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setSession(mockHttpSession);
    }


    @AfterEach
    public void clear(){
        mockHttpSession.clearAttributes();
        mockHttpSession = null;
    }


    @Test
    void 기본_로그인_페이지() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
               .andExpect(view().name("/index"))
                .andExpect(model().attributeExists("MemberLogin"));

    }

    @Test
    void 로그인_요청_테스트() throws Exception {
        mockMvc.perform(get("/").session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(view().name("/index"));
    }


}