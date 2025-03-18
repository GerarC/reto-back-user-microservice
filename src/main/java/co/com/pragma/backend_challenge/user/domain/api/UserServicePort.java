package co.com.pragma.backend_challenge.user.domain.api;

import co.com.pragma.backend_challenge.user.domain.model.User;

public interface UserServicePort {
    User createOwner(User user);
    boolean isOwner(String id);
    User findById(String id);
    User createEmployee(User user, String restaurantId);
    User createCustomer(User user);
}
