package refresh.ManageSystem.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    /**
     * Daniel Kim
     *
     * 달력 정보 가져오기(연 월)
     * @throws Exception
     *
     * 2023-04-17
     */
    @Test
    void 달력_파라미터_요청() throws Exception {
        mvc.perform(get("/calendar?year=2023&month=5"))
                .andExpect(status().isOk());
    }

    /**
     * Daniel Kim
     *
     * 이름을 통해 연차 정보 가져오기
     * @throws Exception
     *
     * 2023-04-29
     */
    @Test
    void 이름_파라미터_요청() throws Exception {
        mvc.perform(get("/calendar/annual"))
                .andExpect(status().isOk());
    }
}