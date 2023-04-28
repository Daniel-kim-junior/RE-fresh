package refresh.ManageSystem.service;

import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.repository.DepartmentRepository;

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
