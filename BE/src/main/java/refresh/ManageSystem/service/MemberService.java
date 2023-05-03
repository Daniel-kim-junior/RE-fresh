package refresh.ManageSystem.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.dao.MemberDAO;
import refresh.ManageSystem.repository.MemberRepository;

/**
 * Daniel Kim
 *
 * MemberService : 회원 관련 서비스 클래스
 *
 * 2021-05-01
 */
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


}
