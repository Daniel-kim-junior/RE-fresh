package refresh.ManageSystem.controller.annual;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.util.hash.SHA256;

/**
 * Daniel Kim
 *
 * AnnualRestController("/annual") RestAPI 테스트
 *
 * 2023-05-02
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AnnualRestControllerTest {
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
    }
    @AfterAll
    static void 세션_해제() {
        httpSession.clearAttributes();
        httpSession = null;
    }

    /**
     * Daniel Kim
     *
     * 사원 이름으로 연차 정보 검색
     * @throws Exception
     *
     * 2023-05-02
     */
    @Test
    void 사원_이름으로_연차_정보_검색() throws Exception {
        String expectByMemberName = "$.[?(@.name == '%s')]";

        mockMvc.perform(get("/annual/member?name=김민&start=0&end=10").session(httpSession))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(expectByMemberName, "김민성").exists());
    }

    /**
     * Daniel Kim
     *
     * 부서 이름으로 연차 정보 검색
     * @throws Exception
     *
     * 2023-05-02
     */
    @Test
    void 부서_이름으로_연차_정보_검색() throws Exception {
        String expectByMemberName = "$.[?(@.name == '%s')]";

        mockMvc.perform(get("/annual/department?name=개발팀&start=0&end=10").session(httpSession))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(expectByMemberName, "박주희").exists());
    }

}