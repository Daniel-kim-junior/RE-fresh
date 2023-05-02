package refresh.ManageSystem.controller.calendar;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.util.hash.SHA256;

/**
 * Daniel Kim
 *
 * CalenderRestController("/calendar) 테스트
 * CalendarRestController("/calendar/annual") 테스트
 *
 * 2023-04-17
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CalendarRestControllerTest {
    @Autowired
    private MockMvc mvc;

    private static SHA256 sha256;
    private static MockHttpSession session;
    private static MemberLoginDTO loginDTO;
    private static MockHttpServletRequest request;
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


    /**
     * Daniel Kim
     *
     * 부서 이름과 세션 정보를 통해 달력 정보를 요청하는 테스트
     * @throws Exception
     *
     * 2023-04-29
     */
    @Test
    void 부서_이름_달력_정보_요청() throws Exception {
        mvc.perform(get("/calendar/department?year=2023&month=10&departmentName=개발팀"))
                .andExpect(status().isOk());
    }

    @Test
    void 세션_정보_달력_정보_요청() throws Exception {
        mvc.perform(get("/calendar?year=2023&month=10").session(session))
                .andExpect(status().isOk());

    }
}