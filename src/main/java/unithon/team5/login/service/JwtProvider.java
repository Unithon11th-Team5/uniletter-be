package unithon.team5.login.service;

import static io.jsonwebtoken.io.Decoders.BASE64;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  public static final String MEMBER_ID_IDENTIFIER = "memberId";
  private static final String TOKEN_ISSUER = "secret Message";
  private static final int DURATION_DAY = 90;
  private final String encodedSecretKey;

  public JwtProvider(@Value("${jwt.secret}") final String encodedSecretKey) {
    this.encodedSecretKey = encodedSecretKey;
  }

  public String createAccessTokenWith(final UUID id) {
    final Date now = new Date();
    final Date expiration
        = new Date(now.getTime() + Duration.ofDays(DURATION_DAY).toMillis());
    final Map<String, Object> claims = Map.of(MEMBER_ID_IDENTIFIER, id.toString());

    return Jwts.builder()
        .issuer(TOKEN_ISSUER)
        .issuedAt(now)
        .expiration(expiration)
        .claims(claims)
        .signWith(Keys.hmacShaKeyFor(BASE64.decode(encodedSecretKey)))
        .compact();
  }

  public UUID parseMemberId(final String jwt) {
    final String plainUUID = (String) Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(BASE64.decode(encodedSecretKey)))
        .build()
        .parseSignedClaims(jwt)
        .getPayload()
        .get(MEMBER_ID_IDENTIFIER);
    return UUID.fromString(plainUUID);
  }
}
