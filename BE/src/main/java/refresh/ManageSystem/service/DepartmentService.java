package refresh.ManageSystem.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.repository.DepartmentRepository;

/**
 * Daniel Kim
 *
 * 부서 정보를 가져오는 서비스
 * getDepartmentNameById: memberId를 통해 해당 유저의 부서 이름을 가져옴
 * getDepartmentTotalById: memberId를 통해 해당 유저의 부서 연차 총합을 가져옴
 *
 * 2023-04-28
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Optional<String> getDepartmentNameById(String memberId) {
        return departmentRepository.getDepartmentNameById(memberId);
    }

    public Optional<Integer> getDepartmentTotalById(String memberId) {
        return departmentRepository.getDepartmentTotalById(memberId);
    }
}
