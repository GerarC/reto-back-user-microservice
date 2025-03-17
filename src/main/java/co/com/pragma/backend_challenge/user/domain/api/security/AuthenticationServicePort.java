package co.com.pragma.backend_challenge.user.domain.api.security;

import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticatedUser;

public interface AuthenticationServicePort {
    AuthenticatedUser authenticate(String email, String password);
    AuthenticatedUser validateToken(String token);
}
