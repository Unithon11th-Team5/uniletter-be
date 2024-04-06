package unithon.team5.login;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.apple")
public record AppleOauthConfig(
    String clientId,
    String nonce,
    String redirectUri,
    String responseMode,
    String responseType,
    String scope,
    String state
) {

}
