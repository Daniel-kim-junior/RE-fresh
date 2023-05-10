package refresh.ManageSystem.controller.member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import refresh.ManageSystem.dto.MemberLoginDTO;
import refresh.ManageSystem.dto.MemberServiceDTO;
import refresh.ManageSystem.service.MemberService;
import refresh.ManageSystem.util.hash.SHA256;



@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql("classpath:db/MakeTable.sql")
@Sql("classpath:db/MakeDepartment.sql")
@Sql("classpath:db/MakeMember.sql")
@Sql("classpath:db/MakeAnnual.sql")
@Sql("classpath:db/MakeAnnualCount.sql")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberService memberService;

    private static MockHttpSession httpSession;
    private static MockHttpServletRequest httpServletRequest;
    private static SHA256 sha256;
    private static  MemberLoginDTO memberLoginDTO;
    @BeforeAll
    static void setUp() throws Exception {
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

    @Test
    @Transactional
    @Rollback
    void 권한_없을때_302_페이지_리다이렉트() throws Exception {
        mockMvc.perform(get("/admin/members/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth"));
    }

    @Test
    @Transactional
    @Rollback
    void 멤버_생성_페이지_이동() throws Exception {
        mockMvc.perform(get("/admin/members/new").session(httpSession))
               .andExpect(status().isOk())
                .andExpect(model().attributeExists("memberInfoVO"))
               .andExpect(view().name("/pages/admin/member/createMemberForm"));
    }

    @Test
    @Transactional
    @Rollback
    void 멤버_생성_기능() throws Exception {
        // Given
        MemberServiceDTO memberServiceDTO = new MemberServiceDTO();

        memberServiceDTO.setMemberId("admin");
        memberServiceDTO.setMemberPassword("1234");
        memberServiceDTO.setDepartmentName("개발팀");
        memberServiceDTO.setMemberName("admin");
        memberServiceDTO.setMemberEmail("now@gmail.com");
        memberServiceDTO.setMemberCellphone("010-1234-1234");
        memberServiceDTO.setMemberAuth("admin");
        memberServiceDTO.setCreateId("super");
        memberServiceDTO.setUpdateId("super");
        // when(check == true)
        boolean res = memberService.checkId(memberServiceDTO);

        // then
        assertThat(res).isTrue();

        // give & when
        String content = new ObjectMapper().writeValueAsString(memberServiceDTO);

        // then
        mockMvc.perform(post("/admin/members/new").session(httpSession).content(content))
                .andExpect(status().isOk())
               .andExpect(view().name("/pages/admin/member/createMemberForm"));

    }







}