package refresh.ManageSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import refresh.ManageSystem.dao.AnnualSearchDAO;
import refresh.ManageSystem.dao.AnnualStatusDAO;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.dto.AnnualSearchDTO;
import refresh.ManageSystem.repository.AnnualManageRepository;
import refresh.ManageSystem.vo.AnnualManageVO;
import refresh.ManageSystem.vo.AnnualStatusVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Park JuHee
 *
 * 관리자 페이지의 연차 관리 서비스 클래스
 *
 * 2023-05-01
 */

@Service
public class AnnualManageService {

    @Autowired
    private AnnualManageRepository  annualManageRepository;

    /**
     * Park JuHee
     * 연차 신청의 전체 리스트를 불러와서 dateFormat 후 반환
     * 2023-05-01
     */
    public List<AnnualManageDTO> getAnnualManageList(){

        return voToDTO(annualManageRepository.getAnnualManageList());
    }

    public List<AnnualManageDTO> getAnnualSearchList(AnnualSearchDTO searchDTO){
        List<AnnualManageVO> list =  annualManageRepository.getAnnualSearchList(AnnualSearchDAO
                .builder()
                .departmentName(searchDTO.getDepartmentName())
                .memberName(searchDTO.getMemberName())
                .startDate(searchDTO.getStartDate())
                .endDate(searchDTO.getEndDate())
                .build());

         return voToDTO(list);
    }

    /**
     * Park JuHee
     * 연차 수락,반려값을 bool 타입에서 string으르 변환하여 update 하는 서비스 메소드
     * 2023-05-03
     * */
    public boolean updateAnnualStatus(AnnualStatusVO statusVO,String memberName){
        String approved = statusVO.isStatus() ? "승인" : "반려" ;

         return annualManageRepository.updateAnnualStatus(AnnualStatusDAO
                 .builder()
                 .uid(statusVO.getUid())
                 .acceptor(memberName)
                 .status(approved)
                 .build());
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


}
