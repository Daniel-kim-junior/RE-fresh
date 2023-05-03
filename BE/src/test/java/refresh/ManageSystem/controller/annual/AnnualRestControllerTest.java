package refresh.ManageSystem.controller.annual;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

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
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    /**
     * Daniel Kim
     *
     * MockMvc 객체 생성
     * 필터 추가
     * 모든 요청에 대해 print() 수행
     *
     * 2023-05-02
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                                      .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                                      .alwaysDo(print())
                                      .build();
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

        mockMvc.perform(get("/annual/member?name=강감&start=0&end=10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(expectByMemberName, "강감찬").exists());
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

        mockMvc.perform(get("/annual/department?name=개발팀&start=0&end=10"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath(expectByMemberName, "강감찬").exists());
    }

}