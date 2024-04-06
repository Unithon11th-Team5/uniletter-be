package unithon.team5.login.service;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PostExchange;

public interface AppleOauthClient {

  @PostExchange(url = "https://appleid.apple.com/auth/authorize", contentType = APPLICATION_FORM_URLENCODED_VALUE)
  AppleOauthResponse fetchToken(@RequestParam final MultiValueMap<String, String> params);
}
