package unithon.uniletter.login.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

public class AppleTokenValidator {

    public static void validateAppleIdToken(final Map<String, Object> claims, final String email) {
        validateEmail(claims, email);
        validateExp(claims);
        validateIssuer(claims);
    }

    private static void validateIssuer(final Map<String, Object> claims) {
        final String issuer = (String) claims.get("iss");

        if (!issuer.contains("https://appleid.apple.com")) {
            throw new IllegalArgumentException("apple에서 발급한 id token이 아닙니다");
        }
    }

    private static void validateExp(final Map<String, Object> claims) {
        final Integer epochSecond = (Integer) claims.get("exp");
        final Instant instant = Instant.ofEpochSecond(epochSecond);
        final LocalDateTime expTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        if (!expTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Id 토큰의 유효기간이 만료되었습니다.");
        }
    }

    private static void validateEmail(final Map<String, Object> claims, String email) {
        final Object extractedEmail = claims.get("email");
        if (!extractedEmail.equals(email)) {
            throw new IllegalArgumentException("유효하지 않은 id토큰 이거나 이메일입니다.");
        }
    }
}
