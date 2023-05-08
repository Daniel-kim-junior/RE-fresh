package refresh.ManageSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import refresh.ManageSystem.dao.AnnualCountDAO;
import refresh.ManageSystem.dao.AnnualStatusDAO;
import refresh.ManageSystem.dao.AnnualSumCountDAO;
import refresh.ManageSystem.dto.AnnualHistoryDTO;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.repository.AnnualRepository;
import refresh.ManageSystem.repository.AnnualSumCountRepository;
import refresh.ManageSystem.repository.MemberRepository;
import refresh.ManageSystem.vo.AnnualHistoryVO;
import refresh.ManageSystem.vo.AnnualManageVO;
import refresh.ManageSystem.vo.AnnualStatusVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Park JuHee
 *
 * 연차 관련 서비스 클래스
 *
 * 2023-05-01
 */

@Service
public class AnnualManageService {

    @Autowired
    private AnnualRepository annualRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AnnualSumCountRepository annualSumCountRepository;

    @Autowired
    private DepartmentService departmentService;


    /**
     * Park JuHee
     * 연차 신청의 전체 리스트를 불러와서 dateFormat 후 반환
     * 2023-05-01
     */
    public List<AnnualManageDTO> getAnnualManageList(){

        return voToDTO(annualRepository.getAnnualManageList());
    }

//    public List<AnnualManageDTO> getAnnualSearchList(AnnualSearchDTO searchDTO){
//        List<AnnualManageVO> list =  annualRepository.getAnnualSearchList(AnnualSearchDAO
//                .builder()
//                .departmentName(searchDTO.getDepartmentName())
//                .memberName(searchDTO.getMemberName())
//                .startDate(searchDTO.getStartDate())
//                .endDate(searchDTO.getEndDate())
//                .build());
//
//         return voToDTO(list);
//    }

    /**
     * Park JuHee
     * 연차 수락 : 연차 횟수 차감, 연차 상태 변경
     * 연차 반려 : 연차 상태 변경
     * 2023-05-06
     * */
    public boolean updateAnnualStatus(AnnualStatusVO statusVO,String memberName){
        int memResult = 0;
        int sumCountResult = 0;
        if(statusVO.getStatus().equals("승인")){
            Double count = statusVO.getAnnualType().contains("반차") ? 0.5 : (statusVO.getEndDate().getTime() -statusVO.getStartDate().getTime())/ (24*60*60*1000) ;
            // 집계 로직
            sumCountResult = annualSumCountRepository.setAnnualSumCount(AnnualSumCountDAO
                    .builder()
                    .startDate(statusVO.getStartDate())
                    .endDate(statusVO.getEndDate())
                    .departmentName(statusVO.getDepartmentName())
                    .build());

            memResult =  memberRepository.updateAnnulCount(AnnualCountDAO
                        .builder()
                        .annualUid(statusVO.getUid())
                        .count(count)
                        .build());
        }
        int annResult = annualRepository.updateAnnualStatus(AnnualStatusDAO
                                .builder()
                                .uid(statusVO.getUid())
                                .acceptor(memberName)
                                .status(statusVO.getStatus())
                                .build());

         return (memResult > 0 && annResult > 0 && sumCountResult > 0);
    }


    /**
     * Park JuHee
     * 연차 조회시 VO를  DateFormat 해서 DTO로 변환하는 메소드
     * 2023-05-02
     * */
    public List<AnnualManageDTO> voToDTO(List<AnnualManageVO> list){
        List<AnnualManageDTO> result = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        for(AnnualManageVO data: list){
            result.add(AnnualManageDTO.builder()
                    .annualUid(data.getAnnualUid())
                    .memberName(data.getMemberName())
                    .departmentName(data.getDepartmentName())
                    .annualType(data.getAnnualType())
                    .annualStatus(data.getAnnualStatus())
                    .createTm(dateFormat.format(data.getCreateTm()))
                    .startDate(dateFormat.format(data.getStartDate()))
                    .endDate(dateFormat.format(data.getEndDate()))
                    .build());
        };

        return result;
    }

    /**
     * Park JuHee
     * myPage의 연차 신청 내역을 조회하는 서비스 메소드
     * 2023-05-05
     * */
    public List<AnnualHistoryDTO> getAnnualHistoryList(String id){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        List<AnnualHistoryDTO> result = new ArrayList<>();

        for(AnnualHistoryVO data : annualRepository.getAnnualByMemberId(id)){
            result.add(AnnualHistoryDTO
                    .builder()
                            .annualUid(data.getAnnualUid())
                            .annualType(data.getAnnualType())
                            .annualStatus(data.getAnnualStatus())
                            .acceptor(data.getAcceptor())
                            .startDate(dateFormat.format(data.getStartDate()))
                            .endDate(dateFormat.format(data.getEndDate()))
                            .createTm(dateFormat.format(data.getCreateTm()))
                            .build());
        }
        return result;
    }

    /**
     * Park JuHee
     * 연차 취소 서비스
     * */

    public boolean cancelAnnualRequest(AnnualStatusVO statusVO, String deptName){
        int memResult = 0;
        int sumCountResult = 0;
        if(statusVO.getStatus().equals("승인")){
            Double count =statusVO.getAnnualType().contains("반차") ? 0.5 : (statusVO.getEndDate().getTime() -statusVO.getStartDate().getTime())/ (24*60*60*1000) ;
            sumCountResult = annualSumCountRepository.decreaseAnnualSumCount(AnnualSumCountDAO
                    .builder()
                    .startDate(statusVO.getStartDate())
                    .endDate(statusVO.getEndDate())
                    .departmentName(deptName)
                    .build());

            memResult =  memberRepository.addAnnulCount(AnnualCountDAO
                    .builder()
                    .annualUid(statusVO.getUid())
                    .count(count)
                    .build());
        }
        int annResult = annualRepository.updateAnnualStatus(AnnualStatusDAO
                .builder()
                .uid(statusVO.getUid())
                .status("취소")
                .build());

        return (memResult > 0 && annResult > 0 && sumCountResult > 0);
    }

    public List<AnnualManageDTO> getAnnualManageListByPage(PageDTO dto) {
        return  voToDTO(annualRepository.getAnnualManageListByPage(dto));
    }

    public int countAnnualSearchList(AnnualSearchDTO dto) {
        return annualRepository.countAnnualSearchList(dto);
    }

    public List<AnnualManageDTO> getAnnualManageSearchList(AnnualSearchDTO dto) {
        return voToDTO(annualRepository.getAnnualSearchList(dto));
    }

}
