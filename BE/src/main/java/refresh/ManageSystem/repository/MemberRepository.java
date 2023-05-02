package refresh.ManageSystem.repository;
import java.util.Optional;
import refresh.ManageSystem.dao.MemberDAO;

/**
 * Daniel Kim
 *
 * login: 로그인 시도
 *
 * 2021-04-30
 */
public interface MemberRepository {
    Optional<String> login(MemberDAO dao);
}
