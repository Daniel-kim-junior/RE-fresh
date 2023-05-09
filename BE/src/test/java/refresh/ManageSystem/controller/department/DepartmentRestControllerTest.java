package refresh.ManageSystem.controller.department;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.util.hash.SHA256;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DepartmentRestControllerTest {
    @Autowired
    private MockMvc mvc;
    private static SHA256 sha256;
    private static MockHttpSession session;
    private static MemberLoginDTO loginDTO;
    private static MockHttpServletRequest request;
    /**
     * Daniel Kim
     *
     * MockMvc 객체 생성
     * 필터 추가
     * 모든 요청에 대해 print() 수행
     * session 객체 생성
     * MockHttpServletRequest 객체 생성
     *
     * 2023-05-01
     */
    @BeforeAll
    public static void setUp() throws Exception{
        loginDTO = new MemberLoginDTO();
        sha256 = new SHA256();
        loginDTO.setId("admin");
        loginDTO.setPassword(sha256.getHash("1234", "SHA-256"));
        session = new MockHttpSession();
        session.setAttribute("MemberLogin", loginDTO);

        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterAll
    public static void clear(){
        session.clearAttributes();
        session = null;
    }


    @Autowired
    private MockMvc mockMvc;

    @Test
    void 모든_부서_Get_컨트롤러_테스트() throws Exception {
        mockMvc.perform(get("/department/allList").session(session))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$").isNotEmpty())
               .andExpect(jsonPath("$[0]").isString());
    }

    @Test
    void 세션정보로_부서_초기화_컨트롤러_테스트() throws Exception {
        mockMvc.perform(get("/department").session(session))
               .andExpect(status().isOk())
               .andExpect(jsonPath("department").value("개발팀"));
    }

}