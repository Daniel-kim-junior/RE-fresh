package refresh.ManageSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import refresh.ManageSystem.dao.LeaveRequestDAO;
import refresh.ManageSystem.dto.LeaveRequestDTO;
import refresh.ManageSystem.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;


    /*
    * Kim DaeYeob
    * 연차 신청 서비스
    * 2023-05-07
    *
    * */
    public void insert(LeaveRequestDTO dto, String id, String name) {
        String type = (dto.getLeaveType().equals("full-day") ? "연차" : (dto.getLeaveType().equals("am-half-day") ? "오전 반차" : "오후 반차"));
        leaveRequestRepository.insertAnnual(LeaveRequestDAO
                .builder()
                .name(name)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .leaveType(type)
                .acceptor(id)
                .build());
    }
}
