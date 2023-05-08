package refresh.ManageSystem.repository;

import java.util.List;
import java.util.Optional;

import refresh.ManageSystem.dao.AnnualCountDAO;
import refresh.ManageSystem.dao.MemberDAO;
import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.vo.MemberInfoVO;
import refresh.ManageSystem.vo.MemberVO;
import refresh.ManageSystem.dto.MemberSearchDTO;
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
    String getAuthority(MemberDAO dao);
    String getMemberName(MemberDAO dao);

    void createMember(MemberServiceDTO dto);

    boolean checkId(String memberId);


    double getAnnualCountById(String memberId);

    List<MemberVO> getMemberList();

    List<MemberVO> getMemberSearchList(MemberSearchDTO dto);

    List<MemberVO> getMemberListByPage(PageDTO dto);

    int countMemberSearchList(MemberSearchDTO dto);

    MemberInfoVO getMemberInfo(String memberId);

    int updateAnnulCount(AnnualCountDAO annualCountDAO);

    int addAnnulCount(AnnualCountDAO annualCountDAO);

}
