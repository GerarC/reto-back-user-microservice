package co.com.pragma.backend_challenge.user.infrastructure.configuration;

import co.com.pragma.backend_challenge.user.domain.api.UserServicePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RolePersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.usecase.UserUseCase;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Generated
@Configuration
public class BeanConfiguration {
    // Service Ports
    @Bean
    public UserServicePort userServicePort(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort){
        return new UserUseCase(userPersistencePort, rolePersistencePort);
    }

    // Security
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
