package co.com.pragma.backend_challenge.user.infrastructure.configuration;

import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.RoleEntity;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;

    @Bean
    public CommandLineRunner init(){
        return args -> {
            if(!roleRepository.findAll().isEmpty()) return;

            List<RoleEntity> entities = List.of(
                    RoleEntity.builder().name(RoleName.ADMIN).build(),
                    RoleEntity.builder().name(RoleName.OWNER).build(),
                    RoleEntity.builder().name(RoleName.EMPLOYEE).build(),
                    RoleEntity.builder().name(RoleName.CUSTOMER).build()
            );
            roleRepository.saveAll(entities);
        };
    }
}
