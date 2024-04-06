package unithon.team5.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unithon.team5.login.dto.JwtTokenResponse;
import unithon.team5.login.dto.LoginRequest;
import unithon.team5.login.service.JwtProvider;
import unithon.team5.login.service.LoginService;
import unithon.team5.member.Member;
import unithon.team5.member.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  //TODO : 지울것들 테스트 필요 없어지면 삭제
  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;

  @PostMapping("/login/apple")
  public ResponseEntity<JwtTokenResponse> registerToken(
      @RequestBody final LoginRequest loginRequest
  ) {
    final var token = loginService.createToken(loginRequest.token(), loginRequest.email());
    return ResponseEntity.ok(new JwtTokenResponse(token));
  }

  //TODO: JWT 테스트를 위한 API 11 추후 삭제
  @GetMapping("/loginCheck")
  public ResponseEntity<JwtTokenResponse> testToken(final Member member) {
    final var token = jwtProvider.createAccessTokenWith(member.getId());
    System.out.println(member);
    return ResponseEntity.ok(new JwtTokenResponse(token));
  }

  //TODO: JWT 테스트를 위한 API 22 추후 삭제
  @GetMapping("/tokenRegisterCheck")
  public ResponseEntity<JwtTokenResponse> createToken() {
    final Member member = memberRepository.save(new Member("닉네임", "크리덴셜"));
    final String token = jwtProvider.createAccessTokenWith(member.getId());
    return ResponseEntity.ok(new JwtTokenResponse(token));
  }
}
