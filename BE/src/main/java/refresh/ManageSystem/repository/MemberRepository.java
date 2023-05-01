package refresh.ManageSystem.repository;
import java.util.Optional;
import refresh.ManageSystem.dao.MemberDAO;

public interface MemberRepository {
    Optional<String> login(MemberDAO dao);
}
