package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository;

import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByIdentityDocument(String identityDocument);
}
