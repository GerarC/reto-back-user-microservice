package co.com.pragma.backend_challenge.user.domain.usecase.security;

import co.com.pragma.backend_challenge.user.domain.api.security.AuthenticationServicePort;
import co.com.pragma.backend_challenge.user.domain.exception.ExpiredTokenException;
import co.com.pragma.backend_challenge.user.domain.exception.InvalidTokenException;
import co.com.pragma.backend_challenge.user.domain.exception.UserNotRegisteredException;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticatedUser;
import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticationInfo;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.security.AuthenticationSecurityPort;
import co.com.pragma.backend_challenge.user.domain.spi.security.TokenSecurityPort;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;

public class AuthenticationUseCase implements AuthenticationServicePort {
    private final TokenSecurityPort tokenSecurityPort;
    private final AuthenticationSecurityPort authenticationSecurityPort;
    private final UserPersistencePort userPersistencePort;

    public AuthenticationUseCase(
            TokenSecurityPort tokenSecurityPort,
            AuthenticationSecurityPort authenticationSecurityPort,
            UserPersistencePort userPersistencePort) {
        this.tokenSecurityPort = tokenSecurityPort;
        this.authenticationSecurityPort = authenticationSecurityPort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public AuthenticatedUser authenticate(String email, String password) {
        User user = userPersistencePort.findByEmail(email);
        if(user == null) throw new UserNotRegisteredException();

        AuthenticationInfo authInfo = AuthenticationInfo.builder()
                .id(user.getId())
                .password(password)
                .build();
        authenticationSecurityPort.authenticate(authInfo);

        String token = tokenSecurityPort.createToken(user);
        return AuthenticatedUser.builder()
                .id(user.getId())
                .role(user.getRole().getName())
                .token(token)
                .build();
    }

    @Override
    public AuthenticatedUser validateToken(String token) {
        String userId;
        boolean isValid;
        RoleName userRole;
        try {
            userId = tokenSecurityPort.getUsername(token);
            userRole = userPersistencePort.findById(userId).getRole().getName();
            isValid = tokenSecurityPort.validateToken(token, userId);
        } catch (Exception e){
            throw new InvalidTokenException();
        }
        if(!isValid) throw new ExpiredTokenException();
        return AuthenticatedUser.builder()
                .token(token)
                .role(userRole)
                .id(userId)
                .build();
    }
}
