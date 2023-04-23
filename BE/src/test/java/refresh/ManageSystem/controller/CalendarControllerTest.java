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
 * CalenderController("/calendar) 테스트
 * 2023-04-17
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CalendarControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void 파라미터없는_요청() throws Exception {
        mvc.perform(get("/calendar"))
                .andExpect(status().isOk());
    }

    @Test
    void 파라미터있는_요청() throws Exception {
        mvc.perform(get("/calendar?year=2023&month=5"))
                .andExpect(status().isOk());
    }
}