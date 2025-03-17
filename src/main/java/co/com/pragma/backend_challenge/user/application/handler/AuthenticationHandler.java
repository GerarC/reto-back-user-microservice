package co.com.pragma.backend_challenge.user.application.handler;

import co.com.pragma.backend_challenge.user.application.dto.request.AuthenticationRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.AuthenticationResponse;

public interface AuthenticationHandler {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    AuthenticationResponse validateToken(String token);
}
