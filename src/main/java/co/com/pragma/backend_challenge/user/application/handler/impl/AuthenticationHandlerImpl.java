package co.com.pragma.backend_challenge.user.application.handler.impl;

import co.com.pragma.backend_challenge.user.application.dto.request.AuthenticationRequest;
import co.com.pragma.backend_challenge.user.application.dto.request.AuthorizationRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.AuthenticationResponse;
import co.com.pragma.backend_challenge.user.application.handler.AuthenticationHandler;
import co.com.pragma.backend_challenge.user.application.mapper.response.AuthenticationResponseMapper;
import co.com.pragma.backend_challenge.user.domain.api.security.AuthenticationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationHandlerImpl implements AuthenticationHandler {
    private final AuthenticationServicePort authenticationServicePort;
    private final AuthenticationResponseMapper authenticationResponseMapper;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        return authenticationResponseMapper.toResponse(
                authenticationServicePort.authenticate(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
    }

    @Override
    public AuthenticationResponse validateToken(String token) {
        return authenticationResponseMapper.toResponse(
                authenticationServicePort.validateToken(
                        token
                )
        );
    }
}
