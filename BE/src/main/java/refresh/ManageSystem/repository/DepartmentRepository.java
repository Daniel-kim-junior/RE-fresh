package refresh.ManageSystem.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/**
 * Daniel Kim
 *
 * getDepartmentNameById: memberId를 통해 부서 이름을 가져옴
 * getDepartmentTotalById: memberId를 통해 부서 연차 총합을 가져옴
 *
 * 2023-04-28
 */

@Mapper
public interface DepartmentRepository {
    Optional<String> getDepartmentNameById(String memberId);
    Optional<Integer> getDepartmentTotalById(String memberId);
    List<String> getDepartmentNameList();
    List<String> getDepartmentName();

}
