package co.com.pragma.backend_challenge.user.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {
    private String name;
    private String lastname;
    private String email;
}
