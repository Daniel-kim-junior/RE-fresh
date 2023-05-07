package refresh.ManageSystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import refresh.ManageSystem.dao.MemberDAO;

import refresh.ManageSystem.dto.AnnualCntDTO;

import refresh.ManageSystem.dto.PageDTO;
import refresh.ManageSystem.repository.MemberRepository;
import refresh.ManageSystem.vo.MemberInfoVO;
import refresh.ManageSystem.vo.MemberVO;
import refresh.ManageSystem.dto.MemberSearchDTO;

/**
 * Daniel Kim
 *
 * MemberService : 회원 관련 서비스 클래스
 *
 * 2021-05-01
 */
import refresh.ManageSystem.dto.MemberServiceDTO;


@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    /**
     * 로그인 서비스
     *
     * @param memberId
     * @param memberPassword
     * @return
     * memberId와 memberPassword 를 받아서 로그인을 시도한다.
     * 로그인에 성공하면 Optional 에 memberId를 담아서 반환한다.
     * 로그인에 실패하면 Optional Empty 를 반환한다.
     *
     * 2021-05-01
     */
    public Optional<String> login(String memberId, String memberPassword) {
        return memberRepository.login(MemberDAO.builder()
                                              .memberId(memberId)
                                              .memberPassword(memberPassword)
                                               .build());
    }

    public String getAuthority(String memberId, String memberPassword) {
        return memberRepository.getAuthority(MemberDAO.builder()
                                              .memberId(memberId)
                                              .memberPassword(memberPassword)
                                               .build());
    }


    public String getMemberName(String id, String cryptoPassword) {
        return memberRepository.getMemberName(MemberDAO.builder()
                                              .memberId(id)
                                              .memberPassword(cryptoPassword)
                                               .build());
    }

    public void create(MemberServiceDTO member) {
        memberRepository.createMember(member);
    }

    public boolean checkId(MemberServiceDTO member) {
        return memberRepository.checkId(member.getMemberId());
    }

    public AnnualCntDTO getAnnualCount(String id) {

        return new AnnualCntDTO(memberRepository.getAnnualCountById(id));
    }

    public List<MemberVO> getMemberAllList() {
        return memberRepository.getMemberList();
    }

    public List<MemberVO> getMemberSearchList(MemberSearchDTO dto) {
        return memberRepository.getMemberSearchList(dto);
    }

    public List<MemberVO> getMemberAllListByPage(PageDTO dto) {
        return memberRepository.getMemberListByPage(dto);
    }

    public int countMemberSearchList(MemberSearchDTO dto) {
        return memberRepository.countMemberSearchList(dto);
    }

    public List<MemberInfoVO> getMemberInfo(String memberId) {
        return memberRepository.getMemberInfo(memberId);
    }
}
