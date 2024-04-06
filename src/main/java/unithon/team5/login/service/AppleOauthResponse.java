package unithon.team5.login.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record AppleOauthResponse(
    String code,
    String scope,
    String state,
    AppleOauthUserResponse user
) {

  public record AppleOauthUserResponse(
      UserNameResponse name,
      String email
  ) {

    public record UserNameResponse(String firstName, String lastName) {

    }

  }
}
