package unithon.uniletter.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unithon.uniletter.member.Member;
import unithon.uniletter.member.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findByNickName(final String nickname) {
        return memberRepository.getByNickname(nickname);
    }
}
