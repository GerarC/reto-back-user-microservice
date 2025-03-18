package co.com.pragma.backend_challenge.user.domain.spi.persistence;


import co.com.pragma.backend_challenge.user.domain.model.User;

public interface RestaurantPersistencePort {
    User registerEmployeeInRestaurant(User user, String restaurantId);
}
