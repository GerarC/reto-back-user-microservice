package co.com.pragma.backend_challenge.user.domain.usecase;

import co.com.pragma.backend_challenge.user.domain.api.UserServicePort;
import co.com.pragma.backend_challenge.user.domain.exception.EntityAlreadyExistsException;
import co.com.pragma.backend_challenge.user.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.user.domain.exception.ErrorRegisteringEmployeeInRestaurantException;
import co.com.pragma.backend_challenge.user.domain.exception.UnderAgedUserException;
import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RolePersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.util.DomainConstants;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort,
                       RolePersistencePort rolePersistencePort,
                       RestaurantPersistencePort restaurantPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public User createOwner(User user) {
        return saveUser(user, RoleName.OWNER);
    }

    @Override
    public boolean isOwner(String id) {
        User user = findById(id);
        return user.getRole().getName().equals(RoleName.OWNER);
    }

    @Override
    public User findById(String id) {
        User user = userPersistencePort.findById(id);
        if (user == null) throw new EntityNotFoundException(User.class.getSimpleName(), id);
        return user;
    }

    @Override
    public User createEmployee(User user, String restaurantId) {
        User savedUser = saveUser(user, RoleName.EMPLOYEE);
        registerInRestaurant(savedUser, restaurantId);
        return savedUser;
    }

    private void registerInRestaurant(User user, String restaurantId) {
        try {
            restaurantPersistencePort.registerEmployeeInRestaurant(user, restaurantId);
        } catch (Exception e){
            userPersistencePort.deleteById(user.getId());
            throw new ErrorRegisteringEmployeeInRestaurantException();
        }
    }

    private User saveUser(User user, RoleName rolename) {
        Role role = rolePersistencePort.findByName(rolename);
        if (role == null) throw new EntityNotFoundException(
                String.format(
                        DomainConstants.ROLE_NOT_FOUND_TEMPLATE_MESSAGE,
                        rolename.name()
                )
        );
        user.setRole(role);
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
