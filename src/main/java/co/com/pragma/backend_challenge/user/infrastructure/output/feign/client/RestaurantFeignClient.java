package co.com.pragma.backend_challenge.user.infrastructure.output.feign.client;

import co.com.pragma.backend_challenge.user.infrastructure.configuration.feign.FeignClientConfiguration;
import co.com.pragma.backend_challenge.user.infrastructure.output.feign.dto.request.RestaurantEmployeeRequest;
import co.com.pragma.backend_challenge.user.infrastructure.output.feign.dto.response.RestaurantEmployeeResponse;
import co.com.pragma.backend_challenge.user.infrastructure.output.feign.util.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = FeignConstants.RESTAURANT_CLIENT_NAME,
        url = "${mall.plaza.base-url}/restaurants",
        configuration = FeignClientConfiguration.class
)
public interface RestaurantFeignClient {
    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    RestaurantEmployeeResponse registerEmployee(@RequestBody RestaurantEmployeeRequest restaurantEmployeeRequest);
}
