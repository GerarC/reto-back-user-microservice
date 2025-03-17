package co.com.pragma.backend_challenge.user.infrastructure.output.security.adapters;

import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticationInfo;
import co.com.pragma.backend_challenge.user.domain.spi.security.AuthenticationSecurityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSecurityAdapter implements AuthenticationSecurityPort {
    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(AuthenticationInfo authenticationInfo) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationInfo.getId(),
                        authenticationInfo.getPassword()
                )
        );
    }
}
