package co.com.pragma.backend_challenge.user.application.handler;

import co.com.pragma.backend_challenge.user.application.dto.request.OwnerRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.IsOwnerResponse;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;

public interface UserHandler {
    UserResponse createOwner(OwnerRequest owner);
    IsOwnerResponse isOwner(String id);
}
