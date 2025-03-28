package co.com.pragma.backend_challenge.user.domain.usecase;

import co.com.pragma.backend_challenge.user.domain.exception.EntityAlreadyExistsException;
import co.com.pragma.backend_challenge.user.domain.exception.EntityNotFoundException;
import co.com.pragma.backend_challenge.user.domain.exception.ErrorRegisteringEmployeeInRestaurantException;
import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RestaurantPersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.RolePersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.util.enums.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserUseCaseTest {
    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RolePersistencePort rolePersistencePort;

    @Mock
    private RestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;


    public static final String RESTAURANT_ID = "restaurant-123";
    public static final String USER_ID = "user-id";
    public static final String USER_NAME = "User";
    public static final String USER_LASTNAME = "Dummy";
    public static final String USER_IDENTITY_DOCUMENT = "1223334444";
    public static final String USER_PHONE = "+573225545645";
    public static final String USER_EMAIL = "email@dummy.com";
    public static final String USER_PASSWORD = "password";
    public static final LocalDate USER_BIRTHDATE = LocalDate.now().minusYears(20);
    public static final Long ROLE_ID = 3L;
    public static final RoleName ROLE_NAME = RoleName.OWNER;

    private final Role role = Role.builder()
            .id(ROLE_ID)
            .name(ROLE_NAME)
            .build();
    private final User user = User.builder()
            .name(USER_NAME)
            .lastname(USER_LASTNAME)
            .identityDocument(USER_IDENTITY_DOCUMENT)
            .email(USER_EMAIL)
            .phone(USER_PHONE)
            .birthdate(USER_BIRTHDATE)
            .password(USER_PASSWORD)
            .build();
    private final User expectedUser = User.builder()
            .id(USER_ID)
            .name(USER_NAME)
            .lastname(USER_LASTNAME)
            .identityDocument(USER_IDENTITY_DOCUMENT)
            .email(USER_EMAIL)
            .phone(USER_PHONE)
            .birthdate(USER_BIRTHDATE)
            .password(USER_PASSWORD)
            .role(role)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOwner() {
        // Arrange
        when(userPersistencePort.findByEmail(USER_EMAIL)).thenReturn(null);
        when(userPersistencePort.findByIdentityDocument(USER_IDENTITY_DOCUMENT)).thenReturn(null);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(expectedUser);
        when(rolePersistencePort.findByName(any())).thenReturn(role);

        // Act
        User savedUser = userUseCase.createOwner(user);

        verify(userPersistencePort).findByEmail(USER_EMAIL);
        verify(userPersistencePort).findByIdentityDocument(USER_IDENTITY_DOCUMENT);
        verify(userPersistencePort).saveUser(any(User.class));
        assertEquals(ROLE_NAME, savedUser.getRole().getName());
        assertEquals(USER_EMAIL, savedUser.getEmail());
    }

    @Test
    void createUser_RoleNotFound() {
        // Arrange
        when(rolePersistencePort.findByName(any())).thenReturn(null);

        //Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userUseCase.createOwner(user));
    }

    @Test
    void createUser_EmailAlreadyExists() {
        when(rolePersistencePort.findByName(any())).thenReturn(role);
        when(userPersistencePort.findByEmail(USER_EMAIL)).thenReturn(expectedUser);

        assertThrows(EntityAlreadyExistsException.class, () -> userUseCase.createOwner(user));
    }

    @Test
    void isOwner() {
        when(userPersistencePort.findById(USER_ID)).thenReturn(expectedUser);

        boolean isOwner = userUseCase.isOwner(USER_ID);

        verify(userPersistencePort).findById(USER_ID);
        assertTrue(isOwner);
    }


    @Test
    void isOwner_userIsNotOwner() {
        final User adminUser = User.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .lastname(USER_LASTNAME)
                .identityDocument(USER_IDENTITY_DOCUMENT)
                .email(USER_EMAIL)
                .phone(USER_PHONE)
                .birthdate(USER_BIRTHDATE)
                .password(USER_PASSWORD)
                .role(Role.builder().id(1L).name(RoleName.ADMIN).build())
                .build();
        when(userPersistencePort.findById(USER_ID)).thenReturn(adminUser);

        boolean isOwner = userUseCase.isOwner(USER_ID);

        verify(userPersistencePort).findById(USER_ID);
        assertFalse(isOwner);
    }

    @Test
    void createEmployee_Success() {
        // Arrange

        Role employeeRole = Role.builder().id(2L).name(RoleName.EMPLOYEE).build();
        expectedUser.setRole(employeeRole);

        when(rolePersistencePort.findByName(RoleName.EMPLOYEE)).thenReturn(Role.builder().id(2L).name(RoleName.EMPLOYEE).build());
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(expectedUser);

        // Act
        User savedUser = userUseCase.createEmployee(user, RESTAURANT_ID);

        // Assert
        verify(rolePersistencePort).findByName(RoleName.EMPLOYEE);
        verify(userPersistencePort).saveUser(any(User.class));
        verify(restaurantPersistencePort).registerEmployeeInRestaurant(savedUser, RESTAURANT_ID);
        assertEquals(RoleName.EMPLOYEE, savedUser.getRole().getName());
    }

    @Test
    void createEmployee_RoleNotFound() {
        // Arrange
        when(rolePersistencePort.findByName(RoleName.EMPLOYEE)).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userUseCase.createEmployee(user, "restaurant-123"));
    }

    @Test
    void createEmployee_ErrorRegisteringInRestaurant() {
        // Arrange
        when(rolePersistencePort.findByName(RoleName.EMPLOYEE)).thenReturn(Role.builder().id(2L).name(RoleName.EMPLOYEE).build());
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(expectedUser);
        doThrow(new RuntimeException()).when(restaurantPersistencePort).registerEmployeeInRestaurant(any(User.class), any(String.class));

        // Act & Assert
        assertThrows(ErrorRegisteringEmployeeInRestaurantException.class, () -> userUseCase.createEmployee(user, RESTAURANT_ID));
        verify(userPersistencePort).deleteById(expectedUser.getId());
    }

    @Test
    void createCustomer() {
        Role customerRole = Role.builder().id(2L).name(RoleName.CUSTOMER).build();
        expectedUser.setRole(customerRole);

        when(userPersistencePort.findByEmail(USER_EMAIL)).thenReturn(null);
        when(userPersistencePort.findByIdentityDocument(USER_IDENTITY_DOCUMENT)).thenReturn(null);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(expectedUser);
        when(rolePersistencePort.findByName(any())).thenReturn(role);

        // Act
        User savedUser = userUseCase.createCustomer(user);

        verify(userPersistencePort).findByEmail(USER_EMAIL);
        verify(userPersistencePort).findByIdentityDocument(USER_IDENTITY_DOCUMENT);
        verify(userPersistencePort).saveUser(any(User.class));
        assertEquals(customerRole.getName(), savedUser.getRole().getName());
        assertEquals(USER_EMAIL, savedUser.getEmail());
    }

}