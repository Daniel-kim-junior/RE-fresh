package refresh.ManageSystem.repository;

import java.util.Optional;
import refresh.ManageSystem.dao.MemberDAO;
import refresh.ManageSystem.dto.MemberServiceDTO;
import org.apache.ibatis.annotations.Mapper;
/**
 * Daniel Kim
 *
 * login: 로그인 시도
 * getAuthority: 권한 가져오기
 *
 * 2021-04-30
 */
@Mapper
public interface MemberRepository {
    Optional<String> login(MemberDAO dao);

    void createMember(MemberServiceDTO dto);

    boolean checkId(String memberId);

}
