package unithon.team5.login.service;

import java.util.Map;

public class AppleTokenValidator {

    public static void validateAppleIdToken(final Map<String, Object> claims, final String email) {
        validateEmail(claims, email);
    }

    private static void validateEmail(final Map<String, Object> claims, String email) {
        final Object extractedEmail = claims.get("email");
        if (!extractedEmail.equals(email)) {
            throw new IllegalArgumentException("유효하지 않은 id토큰 이거나 이메일입니다.");
        }
    }
}
