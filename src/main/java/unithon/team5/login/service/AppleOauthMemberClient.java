package unithon.team5.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import unithon.team5.login.AppleOauthConfig;
import unithon.team5.login.OauthMemberInfo;
import unithon.team5.login.dto.LoginRequest;

@Component
@RequiredArgsConstructor
public class AppleOauthMemberClient {

  private final AppleOauthClient appleOauthClient;
  private final AppleOauthConfig appleOauthConfig;

  public OauthMemberInfo getIdentifier(final LoginRequest request) {
    // TODO : 임시
    return new OauthMemberInfo("dafsd", "구글");
  }

  private MultiValueMap<String, String> tokenRequestParams(final String authCode) {
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", appleOauthConfig.clientId());
//    params.add("redirect_uri", appleOauthConfig.redirectUri());
    params.add("code", authCode);
//    params.add("client_secret", appleOauthConfig.clientSecret());
    return params;
  }
}
