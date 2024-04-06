package unithon.uniletter.login;

import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import unithon.uniletter.login.service.JwtProvider;
import unithon.uniletter.member.Member;
import unithon.uniletter.member.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

  private final JwtProvider jwtProvider;
  private final MemberRepository memberRepository;

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    return parameter.getParameterType().equals(Member.class);
  }

  @Override
  public Object resolveArgument(
      final MethodParameter parameter, final ModelAndViewContainer mavContainer,
      final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory
  ) {
    final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    final String token = AuthorizationExtractor.extract(request);

    final UUID uuid = jwtProvider.parseMemberId(token);
    return memberRepository.findById(uuid)
        .orElseThrow(() -> new IllegalArgumentException("존재하는 멤버를 찾을 수 없습니다."));
  }
}
