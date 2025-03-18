package co.com.pragma.backend_challenge.user.application.handler.impl;

import co.com.pragma.backend_challenge.user.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.user.application.dto.request.UserRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.IsOwnerResponse;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;
import co.com.pragma.backend_challenge.user.application.handler.UserHandler;
import co.com.pragma.backend_challenge.user.application.mapper.request.EmployeeRequestMapper;
import co.com.pragma.backend_challenge.user.application.mapper.request.UserRequestMapper;
import co.com.pragma.backend_challenge.user.application.mapper.response.UserResponseMapper;
import co.com.pragma.backend_challenge.user.domain.api.UserServicePort;
import co.com.pragma.backend_challenge.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {
    private final UserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createOwner(UserRequest owner) {
        User user = userRequestMapper.toDomain(owner);
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

    @Override
    public UserResponse createEmployee(EmployeeRequest employeeRequest) {
        User user = employeeRequestMapper.toDomain(employeeRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userResponseMapper.toResponse(
                userServicePort.createEmployee(user, employeeRequest.getRestaurantId())
        );
    }
}
