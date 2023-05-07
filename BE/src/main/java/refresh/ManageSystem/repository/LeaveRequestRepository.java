package refresh.ManageSystem.repository;

import refresh.ManageSystem.dao.LeaveRequestDAO;
import refresh.ManageSystem.dto.LeaveRequestDTO;

public interface LeaveRequestRepository {

    void insertAnnual(LeaveRequestDAO dao);

}