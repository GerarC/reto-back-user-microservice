package co.com.pragma.backend_challenge.user.application.dto.request;

import co.com.pragma.backend_challenge.user.application.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotNull(message = AppConstants.EMAIL_FIELD_NOT_NULL)
    @Pattern(regexp = AppConstants.EMAIL_ADDRESS_REGEX, message = AppConstants.WRONG_EMAIL_FORMAT)
    private String email;

    @NotNull(message = AppConstants.PASSWORD_FIELD_NOT_NULL)
    private String password;
}
