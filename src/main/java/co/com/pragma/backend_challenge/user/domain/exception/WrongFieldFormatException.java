package co.com.pragma.backend_challenge.user.domain.exception;

import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;

public class WrongFieldFormatException extends RuntimeException {
    public WrongFieldFormatException(String field, String value) {
        super(String.format(
                DomainConstants.WRONG_FIELD_FORMAT_TEMPLATE_MESSAGE,
                value, field
        ));
    }
}
