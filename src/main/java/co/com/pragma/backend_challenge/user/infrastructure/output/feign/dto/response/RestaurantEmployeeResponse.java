package co.com.pragma.backend_challenge.user.infrastructure.output.feign.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantEmployeeResponse {
    private String id;
    private String restaurantId;
}
