package co.com.pragma.backend_challenge.user.domain.exception;

import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;

public class ErrorRegisteringEmployeeInRestaurantException extends RuntimeException {
    public ErrorRegisteringEmployeeInRestaurantException() {
        super(DomainConstants.ERROR_REGISTERING_EMPLOYEE_MESSAGE);
    }
}
