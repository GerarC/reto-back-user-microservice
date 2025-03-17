package co.com.pragma.backend_challenge.user.infrastructure.configuration;

import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.RoleEntity;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.UserEntity;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository.RoleRepository;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Generated
@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

            UserEntity user = UserEntity.builder()
                    .identityDocument("244466666")
                    .name("Admin")
                    .lastname("Admin")
                    .birthdate(LocalDate.now().minusYears(30))
                    .phone("+573024546756")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("password")) // NOSONAR
                    .role(roleRepository.findByName(RoleName.ADMIN).orElse(null))
                    .build();
            userRepository.save(user);

        };
    }
}
