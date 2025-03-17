package co.com.pragma.backend_challenge.user.domain.exception;


import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(DomainConstants.INVALID_TOKEN_MESSAGE);
    }
}
