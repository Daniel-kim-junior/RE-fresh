package refresh.ManageSystem.repository;

import java.util.Optional;
import java.util.OptionalInt;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentRepository {
    Optional<String> getDepartmentNameById(String memberId);
    Optional<Integer> getDepartmentTotalById(String memberId);
}
