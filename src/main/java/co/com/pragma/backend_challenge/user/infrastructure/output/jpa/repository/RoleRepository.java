package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository;

import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName name);
}
