package refresh.ManageSystem.controller.annual;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AnnualManageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Daniel Kim
     *
     * 연차 정보를 가져오는 테스트
     *
     * 2023-05-02
     */
    @Test
    void 연차_정보_Get_요청() throws Exception {
        mockMvc.perform(get("/admin/annualManage"))
               .andDo(print())
               .andExpect(status().isOk());
    }

}