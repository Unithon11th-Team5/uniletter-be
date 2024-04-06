package unithon.team5.login.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Component
public class JwtProvider {

    public static final String MEMBER_ID_IDENTIFIER = "memberId";
    private static final String TOKEN_ISSUER = "secret Message";
    private static final int DURATION_DAY = 90;

    private final String encodedSecretKey;
    private final ObjectMapper objectMapper;

    public JwtProvider(@Value("${jwt.secret}") final String encodedSecretKey, final ObjectMapper objectMapper) {
        this.encodedSecretKey = encodedSecretKey;
        this.objectMapper = objectMapper;
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

    public String decodeSub(final String idToken, final String email) {
        try {
            final String claimsBase64 = idToken.substring(idToken.indexOf('.') + 1, idToken.lastIndexOf('.'));
            final var decode = BASE64.decode(claimsBase64);
            final Map<String, Object> claims = objectMapper.readValue(decode, new TypeReference<>() {
            });
            AppleTokenValidator.validateAppleIdToken(claims, email);
            return (String) claims.get("sub");
        } catch (final Exception exception) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }

    private void validateAppleIdToken(final Map<String, Object> claims, final String email) {
        final Object extractedEmail = claims.get("email");
        if (!extractedEmail.equals(email)) {
            throw new IllegalArgumentException("유효하지 않은 id토큰이거나 이메일입니다.");
        }
    }
}
