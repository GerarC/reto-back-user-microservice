package co.com.pragma.backend_challenge.user.infrastructure.output.security.adapters;

import co.com.pragma.backend_challenge.user.domain.exception.InvalidTokenException;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.security.TokenSecurityPort;
import co.com.pragma.backend_challenge.user.infrastructure.output.security.service.JwtService;
import co.com.pragma.backend_challenge.user.infrastructure.output.security.util.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenSecurityAdapter implements TokenSecurityPort {
    private final JwtService jwtService;

    @Override
    public String createToken(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put(
                SecurityConstants.CLAIM_ROLE,
                SecurityConstants.ROLE_PREFIX + user.getRole().getName().name()
        );

        return jwtService.generateToken(claims, user.getId());
    }

    @Override
    public boolean validateToken(String token, String username) {
        try {
            return jwtService.isTokenValid(token, username);
        } catch (InvalidTokenException e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return jwtService.extractUsername(token);
    }
}
