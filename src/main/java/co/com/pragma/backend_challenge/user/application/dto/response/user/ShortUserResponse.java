package co.com.pragma.backend_challenge.user.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String name;
    private String lastname;
    private String phone;
}
