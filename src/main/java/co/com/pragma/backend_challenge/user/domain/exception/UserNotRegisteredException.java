package co.com.pragma.backend_challenge.user.domain.exception;

import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException() {
        super(String.format(DomainConstants.USER_WITH_EMAIL_NOT_FOUND));
    }
}
