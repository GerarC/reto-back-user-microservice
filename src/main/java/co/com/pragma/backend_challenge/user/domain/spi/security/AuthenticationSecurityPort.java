package co.com.pragma.backend_challenge.user.domain.spi.security;


import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticationInfo;

public interface AuthenticationSecurityPort {
    void authenticate(AuthenticationInfo authenticationInfo);
}
