package refresh.ManageSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import refresh.ManageSystem.dao.MemberDAO;
import refresh.ManageSystem.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Optional<String> login(String memberId, String memberPassword) {
        return memberRepository.login(MemberDAO.builder()
                                              .memberId(memberId)
                                              .memberPassword(memberPassword)
                                               .build());
    }



}
