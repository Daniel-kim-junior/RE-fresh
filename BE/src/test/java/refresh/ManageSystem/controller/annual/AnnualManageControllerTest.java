package refresh.ManageSystem.controller.annual;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.util.hash.SHA256;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AnnualManageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static MockHttpSession httpSession;
    private MockHttpServletRequest httpServletRequest;
    private SHA256 sha256;
    private MemberLoginDTO memberLoginDTO;
    private PageDTO pageDTO;
    @BeforeEach
    void setUp() throws Exception {
        sha256 = new SHA256();
        httpServletRequest = new MockHttpServletRequest();
        memberLoginDTO = new MemberLoginDTO();
        memberLoginDTO.setId("admin");
        memberLoginDTO.setPassword(sha256.getHash("1234", "SHA-256"));
        memberLoginDTO.setAuthority("admin");
        httpSession = new MockHttpSession();
        httpSession.setAttribute("MemberLogin", memberLoginDTO);
        httpServletRequest.setSession(httpSession);
//        pageDTO = new PageDTO();
//        pageDTO.setPage(1);
//        pageDTO.setStart(1);
//        pageDTO.setPerPageNum(10);
    }

    @AfterAll
    static void 세션_해제() {
        httpSession.clearAttributes();
        httpSession = null;
    }



    /**
     * Daniel Kim
     *
     * 권한 없을때 302 리다이렉트
     *
     * 2023-05-02
     */
    @Test
    void 연차_정보_Get_요청() throws Exception {
        mockMvc.perform(get("/admin/annualManage"))
               .andDo(print())
               .andExpect(status().is3xxRedirection());
    }


}