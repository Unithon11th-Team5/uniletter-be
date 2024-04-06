package unithon.team5.login.dto;

public record LoginRequest(
    String iss,
    String sub,
    String aud,
    String iat,
    String exp,
    String nonce,
    String email
) {

}
