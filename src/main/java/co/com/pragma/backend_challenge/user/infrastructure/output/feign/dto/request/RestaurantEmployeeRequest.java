package co.com.pragma.backend_challenge.user.infrastructure.output.feign.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantEmployeeRequest {
    private String id;
    private String restaurantId;
}
