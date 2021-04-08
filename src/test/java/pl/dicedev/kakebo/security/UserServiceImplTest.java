package pl.dicedev.kakebo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.exceptions.ExceptionMessages;
import pl.dicedev.kakebo.security.exceptions.KakeboDeleteUserException;
import pl.dicedev.kakebo.security.exceptions.UserAlreadyExistException;
import pl.dicedev.kakebo.security.mapper.UserMapper;
import pl.dicedev.kakebo.security.mapper.UserMapperImpl;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.USER_ALREADY_EXISTS;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    private UserService userService;

    private static final UUID userId = UUID.randomUUID();
    public static final String username = "user@admin.com";
    public static final String password = "passwd";

    @BeforeEach
    public void setUp() {
        UserMapper userMapper = new UserMapperImpl();

        userService = new UserServiceImpl(userDetailsRepository, userMapper);
    }

    @Test
    void shouldReturnUserIdIfUserWasCorrectlyWrittenInDatabase() {
        // given
        var username = "admin@admin.org";
        var password = "admin";
        var hashedPassword = "$2a$06$rECVOz6Vt9ALftZLmFM63.aTNYweE23Mvm8N9YOyq4f3rtb9pu8Zi";
        var user = new AuthUserDto(null, username, password);
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(hashedPassword);
        when(userDetailsRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(userDetailsRepository.save(userEntity)).thenReturn(userEntity);

        // when
        var userId = userService.saveUser(user);

        // then
        assertThat(userId).isNull();

    }

    @Test
    void shouldThrowExceptionWhenUserIsAlreadyInDatabase() {
        // given
        var username = "admin@admin.org";
        var password = "admin";
        var userEntity = new UserEntity();
        var user = new AuthUserDto(null, username, password);
        userEntity.setUsername(username);
        when(userDetailsRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

        // when
        var response = assertThrows(UserAlreadyExistException.class,
                () -> userService.saveUser(user));

        // then
        assertThat(response.getMessage()).isEqualTo(USER_ALREADY_EXISTS);

    }

    @Test
    void shouldVerifyIfUserRepositoryDeleteWasCalled() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUsername(username);
        Optional<UserEntity> userEntityOptional = Optional.of(userEntity);

        AuthUserDto userToDelete = new AuthUserDto(userId, username, password);

        when(userDetailsRepository.findById(userId)).thenReturn(userEntityOptional);

        // when
        userService.deleteUser(userToDelete);

        // then
        verify(userDetailsRepository, times(1)).delete(userEntity);

    }

    @Test
    void shouldThrowExceptionKakeboDeleteUserExceptionWhenThereWasAnErrorDuringDeleteUser() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUsername("fakeUser@com.com");
        Optional<UserEntity> userEntityOptional = Optional.of(userEntity);
        AuthUserDto authUserDto = new AuthUserDto(userId, username, password);

        when(userDetailsRepository.findById(userId)).thenReturn(userEntityOptional);

        // when
        var result = assertThrows(KakeboDeleteUserException.class,
                () -> userService.deleteUser(authUserDto));

        // then
        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(ExceptionMessages.USER_DELETE_ERROR);

    }
}