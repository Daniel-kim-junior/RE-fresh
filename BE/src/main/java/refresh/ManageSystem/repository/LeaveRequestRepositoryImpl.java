package refresh.ManageSystem.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import refresh.ManageSystem.dao.LeaveRequestDAO;
import refresh.ManageSystem.dto.LeaveRequestDTO;

@Repository // 빈으로 등록
public class LeaveRequestRepositoryImpl implements LeaveRequestRepository {

    @Autowired
    SqlSession session;    // sqlsession 주입

    @Override
    public void insertAnnual(LeaveRequestDAO dao) {
        String statement = "refresh.ManageSystem.repository.LeaveRequestRepository.insertAnnual";
        session.insert(statement);
    }
}
