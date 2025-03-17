package co.com.pragma.backend_challenge.user.domain.usecase.security;

import co.com.pragma.backend_challenge.user.domain.api.security.TokenServicePort;
import co.com.pragma.backend_challenge.user.domain.spi.security.TokenSecurityPort;

public class TokenUseCase implements TokenServicePort {
    private final TokenSecurityPort tokenSecurityPort;

    public TokenUseCase(TokenSecurityPort tokenSecurityPort) {
        this.tokenSecurityPort = tokenSecurityPort;
    }

    @Override
    public boolean validateToken(String token, String username) {
        return tokenSecurityPort.validateToken(token, username);
    }

    @Override
    public String getUsername(String token) {
        return tokenSecurityPort.getUsername(token);
    }
}
