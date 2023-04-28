package refresh.ManageSystem.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.OptionalInt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    void 회원ID로_부서이름_찾기() {

        Optional<String> member1 = departmentService.getDepartmentNameById("member1");
        Assertions.assertThat(member1).isEmpty();


        Optional<String> member2 = departmentService.getDepartmentNameById("member2");
        Assertions.assertThat(member2).isEqualTo(Optional.of("개발팀"));
    }

    @Test
    void 회원ID로_부서_사원수_찾기() {
        Optional<Integer> t1 = departmentService.getDepartmentTotalById("member2");
        Assertions.assertThat(t1).isEqualTo(Optional.of(5));

        Optional<Integer> t2 = departmentService.getDepartmentTotalById("member1");
        // 존재하지 않는 id로 조회했을 때 Optional 객체가 비었는지 확인
        Assertions.assertThat(t2).isEmpty();

    }
}