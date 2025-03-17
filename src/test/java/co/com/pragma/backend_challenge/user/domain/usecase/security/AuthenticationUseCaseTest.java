package co.com.pragma.backend_challenge.user.domain.usecase.security;

import co.com.pragma.backend_challenge.user.domain.exception.ExpiredTokenException;
import co.com.pragma.backend_challenge.user.domain.exception.InvalidTokenException;
import co.com.pragma.backend_challenge.user.domain.exception.UserNotRegisteredException;
import co.com.pragma.backend_challenge.user.domain.model.Role;
import co.com.pragma.backend_challenge.user.domain.model.User;
import co.com.pragma.backend_challenge.user.domain.model.authentication.AuthenticatedUser;
import co.com.pragma.backend_challenge.user.domain.spi.persistence.UserPersistencePort;
import co.com.pragma.backend_challenge.user.domain.spi.security.AuthenticationSecurityPort;
import co.com.pragma.backend_challenge.user.domain.spi.security.TokenSecurityPort;
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

class AuthenticationUseCaseTest {
    @Mock
    private TokenSecurityPort tokenSecurityPort;

    @Mock
    private AuthenticationSecurityPort authenticationSecurityPort;

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;


    private static final String MOCK_TOKEN = "token";
    public static final String USER_ID = "user-id";
    public static final String USER_NAME = "User";
    public static final String USER_LASTNAME = "Dummy";
    public static final String USER_IDENTITY_DOCUMENT= "1223334444";
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
    private final User user  = User.builder()
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
    void authenticate() {
        when(userPersistencePort.findByEmail(USER_EMAIL)).thenReturn(user);
        doNothing().when(authenticationSecurityPort).authenticate(any());
        when(tokenSecurityPort.createToken(any())).thenReturn(MOCK_TOKEN);

        AuthenticatedUser authUser = authenticationUseCase.authenticate(USER_EMAIL, USER_PASSWORD);

        verify(authenticationSecurityPort).authenticate(any());
        assertEquals(USER_ID, authUser.getId());
        assertEquals(ROLE_NAME, authUser.getRole());
        assertEquals(MOCK_TOKEN, authUser.getToken());
    }

    @Test
    void authenticate_userNotRegistered() {
        when(userPersistencePort.findByEmail(USER_EMAIL)).thenReturn(null);


        assertThrows(UserNotRegisteredException.class,
                () -> authenticationUseCase.authenticate(USER_EMAIL, USER_PASSWORD));

        verify(authenticationSecurityPort, times(0)).authenticate(any());
    }

    @Test
    void validateToken() {
        when(tokenSecurityPort.getUsername(MOCK_TOKEN)).thenReturn(USER_ID);
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(tokenSecurityPort.validateToken(MOCK_TOKEN, USER_ID)).thenReturn(true);

        AuthenticatedUser authUser = authenticationUseCase.validateToken(MOCK_TOKEN);

        assertEquals(USER_ID, authUser.getId());
        assertEquals(ROLE_NAME, authUser.getRole());
        assertEquals(MOCK_TOKEN, authUser.getToken());
    }

    @Test
    void validateToken_expiredToken() {
        when(tokenSecurityPort.getUsername(MOCK_TOKEN)).thenReturn(USER_ID);
        when(userPersistencePort.findById(USER_ID)).thenReturn(user);
        when(tokenSecurityPort.validateToken(MOCK_TOKEN, USER_ID)).thenReturn(false);

        assertThrows(ExpiredTokenException.class,
                () -> authenticationUseCase.validateToken(MOCK_TOKEN));
    }

    @Test
    void validateToken_notUserFound() {
        when(tokenSecurityPort.getUsername(MOCK_TOKEN)).thenReturn(USER_ID);
        when(userPersistencePort.findById(USER_ID)).thenReturn(null);
        when(tokenSecurityPort.validateToken(MOCK_TOKEN, USER_ID)).thenReturn(true);

        assertThrows(InvalidTokenException.class,
                () -> authenticationUseCase.validateToken(MOCK_TOKEN));

        verify(tokenSecurityPort, times(0)).validateToken(any(), any());
    }
}