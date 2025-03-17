package co.com.pragma.backend_challenge.user.domain.spi.persistence;

import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;

public interface RolePersistencePort {
    Role findByName(RoleName name);
}
