package co.com.pragma.backend_challenge.user.infrastructure.configuration.advisor.response;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionResponse {
    private Integer statusCode;
    private HttpStatusCode status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;
}