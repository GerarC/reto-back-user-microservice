package co.com.pragma.backend_challenge.user.domain.spi.persistence;

import co.com.pragma.backend_challenge.user.domain.model.User;

public interface UserPersistencePort {
    User saveUser(User user);
    User findByIdentityDocument(String identityDocument);
    User findByEmail(String email);
    User findById(String id);
}
