package refresh.ManageSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import refresh.ManageSystem.dto.AnnualManageDTO;
import refresh.ManageSystem.repository.AnnualManageRepository;
import refresh.ManageSystem.vo.AnnualManageVO;

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
        List<AnnualManageVO> annualManageList = annualManageRepository.getAnnualManageList();
        List<AnnualManageDTO> result = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        for(AnnualManageVO data: annualManageList){
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
