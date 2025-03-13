package co.com.pragma.backend_challenge.user.domain.usecase;

import co.com.pragma.backend_challenge.user.domain.api.UserServicePort;
import co.com.pragma.backend_challenge.user.domain.exception.EntityAlreadyExistsException;
import co.com.pragma.backend_challenge.user.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.user.domain.exception.UnderAgedUserException;
import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RolePersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public User createOwner(User user) {
        Role role = rolePersistencePort.findByName(RoleName.OWNER);
        if (role == null) throw new EntityNotFoundException(
                String.format(
                        DomainConstants.ROLE_NOT_FOUND_TEMPLATE_MESSAGE,
                        RoleName.OWNER.name()
                )
        );
        user.setRole(role);
        return saveUser(user);
    }

    private User saveUser(User user) {
        validateUser(user);
        return userPersistencePort.saveUser(user);
    }

    private void validateUser(User user) {
        if (userPersistencePort.findByIdentityDocument(user.getIdentityDocument()) != null)
            throw new EntityAlreadyExistsException(String.format(
                    DomainConstants.USER_WITH_IDENTITY_DOCUMENT_ALREADY_EXIST_TEMPLATE_MESSAGE,
                    user.getIdentityDocument()
            ));
        if (userPersistencePort.findByEmail(user.getEmail()) != null)
            throw new EntityAlreadyExistsException(String.format(
                    DomainConstants.USER_WITH_EMAIL_ALREADY_EXIST_TEMPLATE_MESSAGE,
                    user.getEmail()
            ));
        if (user.getBirthdate().until(LocalDate.now(), ChronoUnit.YEARS) < DomainConstants.MINIMUM_VALID_YEARS)
            throw new UnderAgedUserException();
    }
}
