package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RolePersistencePort;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.mapper.RoleEntityMapper;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements RolePersistencePort {
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role findByName(RoleName name) {
        return roleEntityMapper.toDomain(
                roleRepository.findByName(name).orElse(null)
        );
    }
}
