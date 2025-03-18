package co.com.pragma.backend_challenge.user.infrastructure.output.jpa.adapter;

import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.entity.UserEntity;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.mapper.UserEntityMapper;
import co.com.pragma.backend_challenge.user.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity entity = userEntityMapper.toEntity(user);
        log.debug("user: {}", entity);
        return userEntityMapper.toDomain(
                userRepository.save(entity)
        );
    }

    @Override
    public User findByIdentityDocument(String identityDocument) {
        return userEntityMapper.toDomain(
                userRepository.findByIdentityDocument(identityDocument).orElse(null)
        );
    }

    @Override
    public User findByEmail(String email) {
        return userEntityMapper.toDomain(
                userRepository.findByEmail(email).orElse(null)
        );
    }

    @Override
    public User findById(String id) {
        return userEntityMapper.toDomain(
                userRepository.findById(id).orElse(null)
        );
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
