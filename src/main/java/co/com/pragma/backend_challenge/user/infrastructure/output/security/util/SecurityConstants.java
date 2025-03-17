package co.com.pragma.backend_challenge.user.infrastructure.output.security.util;

public class SecurityConstants {
    private SecurityConstants() {
        throw new IllegalStateException("Security Utility class");
    }

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String CLAIM_ROLE = "role";
}
