package co.com.pragma.backend_challenge.user.infrastructure.configuration;

import co.com.pragma.backend_challenge.user.domain.api.UserServicePort;
import co.com.pragma.backend_challenge.user.domain.api.security.AuthenticationServicePort;
import co.com.pragma.backend_challenge.user.domain.api.security.TokenServicePort;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RolePersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.security.AuthenticationSecurityPort;
import co.com.pragma.backend_challenge.user.domain.spi.security.TokenSecurityPort;
import co.com.pragma.backend_challenge.user.domain.usecase.UserUseCase;
import co.com.pragma.backend_challenge.user.domain.usecase.security.AuthenticationUseCase;
import co.com.pragma.backend_challenge.user.domain.usecase.security.TokenUseCase;
import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static co.com.pragma.backend_challenge.user.infrastructure.util.constant.ConfigurationConstants.ROLE_PREFIX;

@Generated
@Configuration
public class BeanConfiguration {
    // Service Ports
    @Bean
    public UserServicePort userServicePort(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort){
        return new UserUseCase(userPersistencePort, rolePersistencePort);
    }

    @Bean
    public AuthenticationServicePort authenticationServicePort(
            TokenSecurityPort tokenSecurityPort,
            AuthenticationSecurityPort authenticationSecurityPort,
            UserPersistencePort userPersistencePort ){
        return new AuthenticationUseCase(
                tokenSecurityPort,
                authenticationSecurityPort,
                userPersistencePort
        );
    }

    @Bean
    public TokenServicePort tokenServicePort(TokenSecurityPort tokenSecurityPort){
        return new TokenUseCase(tokenSecurityPort);
    }

    // Security
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserServicePort userServicePort) {
        return username -> {
            User domainUser = userServicePort.findById(username);
            return new org.springframework.security.core.userdetails.User(
                    domainUser.getId(),
                    domainUser.getPassword(),
                    List.of(new SimpleGrantedAuthority(ROLE_PREFIX + domainUser.getRole().getName().name()))
            );
        };
    }

    @Bean
    AuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
