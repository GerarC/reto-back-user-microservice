package co.com.pragma.backend_challenge.user.domain.api.security;

public interface TokenServicePort {
    boolean validateToken(String token, String username);
    String getUsername(String token);
}
