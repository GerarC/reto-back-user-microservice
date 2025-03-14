package co.com.pragma.backend_challenge.user.application.handler.impl;

import co.com.pragma.backend_challenge.user.application.dto.request.OwnerRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.IsOwnerResponse;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;
import co.com.pragma.backend_challenge.user.application.handler.UserHandler;
import co.com.pragma.backend_challenge.user.application.mapper.OwnerRequestMapper;
import co.com.pragma.backend_challenge.user.application.mapper.UserResponseMapper;
import co.com.pragma.backend_challenge.user.domain.api.UserServicePort;
import co.com.pragma.backend_challenge.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {
    private final UserServicePort userServicePort;
    private final OwnerRequestMapper ownerRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createOwner(OwnerRequest owner) {
        User user = ownerRequestMapper.toDomain(owner);
        user.setPassword(passwordEncoder.encode(owner.getPassword()));
        return userResponseMapper.toResponse(
                userServicePort.createOwner(user)
        );
    }

    @Override
    public IsOwnerResponse isOwner(String id) {
        return IsOwnerResponse.builder()
                .isOwner(userServicePort.isOwner(id))
                .build();
    }
}
