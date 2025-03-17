package co.com.pragma.backend_challenge.user.application.dto.response;


import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
    private RoleName role;
    private String id;
}
