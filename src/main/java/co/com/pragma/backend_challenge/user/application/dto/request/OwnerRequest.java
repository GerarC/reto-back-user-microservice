package co.com.pragma.backend_challenge.user.application.dto.request;

import co.com.pragma.backend_challenge.user.application.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OwnerRequest {
    @NotNull(message = AppConstants.IDENTITY_DOCUMENT_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.IDENTITY_DOCUMENT_REGEX, message = AppConstants.WRONG_IDENTITY_DOCUMENT_FORMAT)
    private String identityDocument;

    @NotNull(message = AppConstants.NAME_FIELD_NOT_NULL)
    private String name;

    @NotNull(message = AppConstants.LASTNAME_FIELD_NOT_NULL)
    private String lastname;

    @Past(message = AppConstants.BIRTHDATE_MUST_BE_PAST_DATE)
    @NotNull(message = AppConstants.BIRTHDATE_FIELD_NOT_NULL)
    private LocalDate birthdate;

    @NotNull(message = AppConstants.PHONE_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.PHONE_NUMBER_REGEX, message = AppConstants.WRONG_PHONE_FORMAT)
    private String phone;

    @NotNull(message = AppConstants.EMAIL_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.EMAIL_ADDRESS_REGEX, message = AppConstants.WRONG_EMAIL_FORMAT)
    private String email;

    @NotNull(message = AppConstants.PASSWORD_FIELD_NOT_NULL)
    private String password;
}
