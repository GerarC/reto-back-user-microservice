package co.com.pragma.backend_challenge.user.application.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationRequest {
    private String token;
}
