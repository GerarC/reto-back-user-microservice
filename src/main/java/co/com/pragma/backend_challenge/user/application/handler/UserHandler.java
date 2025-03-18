package co.com.pragma.backend_challenge.user.application.handler;

import co.com.pragma.backend_challenge.user.application.dto.request.EmployeeRequest;
import co.com.pragma.backend_challenge.user.application.dto.request.UserRequest;
import co.com.pragma.backend_challenge.user.application.dto.response.IsOwnerResponse;
import co.com.pragma.backend_challenge.user.application.dto.response.UserResponse;

public interface UserHandler {
    UserResponse createOwner(UserRequest owner);
    IsOwnerResponse isOwner(String id);
    UserResponse createEmployee(EmployeeRequest employeeRequest);
    UserResponse createCustomer(UserRequest client);
}
