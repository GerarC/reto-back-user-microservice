package co.com.pragma.backend_challenge.user.infrastructure.output.feign.adapter;

import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.user.infrastructure.output.feign.client.RestaurantFeignClient;
import co.com.pragma.backend_challenge.user.infrastructure.output.feign.dto.request.RestaurantEmployeeRequest;
import co.com.pragma.backend_challenge.user.infrastructure.output.feign.dto.response.RestaurantEmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantFeignAdapter implements RestaurantPersistencePort {
    private final RestaurantFeignClient restaurantFeignClient;

    @Override
    public User registerEmployeeInRestaurant(User user, String restaurantId) {
        RestaurantEmployeeRequest request = RestaurantEmployeeRequest.builder()
                .id(user.getId())
                .restaurantId(restaurantId)
                .build();
        RestaurantEmployeeResponse response = restaurantFeignClient.registerEmployee(request);
        return User.builder()
                .id(response.getId())
                .build();
    }
}
