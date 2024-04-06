package unithon.team5.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unithon.team5.member.Member;
import unithon.team5.member.repository.MemberRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Transactional
    public String createToken(final String idToken, final String email) {
        final String memberIdentifier = jwtProvider.decodeSub(idToken, email);
        final Member registeredMember = memberRepository.findByIdentifier(memberIdentifier)
                .orElseGet(() -> registerMember(memberIdentifier, email));
        final UUID id = registeredMember.getId();
        return jwtProvider.createAccessTokenWith(id);
    }

    private Member registerMember(final String memberAppleIdentifier, final String email) {
        final String nickname = parseEmail(email);
        final Member member = Member.builder()
                .nickname(nickname)
                .identifier(memberAppleIdentifier)
                .build();
        return memberRepository.save(member);
    }

    private String parseEmail(final String email) {
        int atIndex = email.indexOf('@');
        return email.substring(0, atIndex);
    }
}
