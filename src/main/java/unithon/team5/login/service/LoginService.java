package unithon.team5.login.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unithon.team5.login.OauthMemberInfo;
import unithon.team5.login.dto.LoginRequest;
import unithon.team5.member.Member;
import unithon.team5.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;
  private final AppleOauthMemberClient appleOauthMemberClient;

  @Transactional
  public String createToken(final LoginRequest loginRequest) {
    final OauthMemberInfo memberInfo = appleOauthMemberClient.getIdentifier(loginRequest);
    final Member registeredMember = memberRepository.findByCredential(memberInfo.credential())
        .orElseGet(() -> registerMember(memberInfo));
    final UUID id = registeredMember.getId();
    return jwtProvider.createAccessTokenWith(id);
  }

  private Member registerMember(final OauthMemberInfo memberInfo) {
    final Member member = Member.builder()
        .nickname(memberInfo.nickName())
        .credential(memberInfo.credential())
        .build();
    return memberRepository.save(member);
  }
}
