package co.com.pragma.backend_challenge.user.domain.exception;

import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;

public class UnderAgedUserException extends RuntimeException {
  public UnderAgedUserException() {
    super(DomainConstants.UNDER_AGED_USER_MESSAGE);
  }
}
