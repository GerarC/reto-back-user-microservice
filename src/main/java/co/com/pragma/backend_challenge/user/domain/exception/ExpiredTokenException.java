package co.com.pragma.backend_challenge.user.domain.exception;

import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super(DomainConstants.EXPIRED_TOKEN_MESSAGE);
    }
}
