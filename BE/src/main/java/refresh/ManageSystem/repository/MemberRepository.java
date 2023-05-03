package refresh.ManageSystem.repository;

import java.util.Optional;
import refresh.ManageSystem.dao.MemberDAO;
import refresh.ManageSystem.dto.MemberServiceDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    Optional<String> login(MemberDAO dao);
    void createMember(MemberServiceDTO dto);
    boolean checkId(String memberId);
}
