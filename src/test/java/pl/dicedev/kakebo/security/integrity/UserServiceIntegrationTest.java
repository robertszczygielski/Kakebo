package pl.dicedev.kakebo.security.integrity;


import org.assertj.core.util.Streams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.UserServiceImpl;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.exceptions.ExceptionMessages;
import pl.dicedev.kakebo.security.exceptions.KakeboDeleteUserException;
import pl.dicedev.kakebo.security.exceptions.UserAlreadyExistException;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.USER_ALREADY_EXISTS;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserServiceImpl userService;

    private final String usernameAndPassword = "user123";

    @Test
    void shouldSaveOneUserInDatabaseWhenThereIsNoUser() {
        // given
        AuthUserDto dto = new AuthUserDto();
        dto.setUsername(usernameAndPassword);
        dto.setPassword(usernameAndPassword);

        // when
        userService.saveUser(dto);

        // then
        var allUsersIterable = this.userDetailsRepository.findAll();
        var allUsers = Streams.stream(allUsersIterable).collect(Collectors.toList());
        assertThat(allUsers).hasSize(1);
        assertThat(allUsers.get(0).getUsername()).isEqualTo(usernameAndPassword);

    }

    @Test
    void shouldThrowExceptionWhenUserNameIsAlreadyInDatabase() {
        // given
        initDatabaseByUser();
        AuthUserDto dto = new AuthUserDto();
        dto.setUsername(this.usernameAndPassword);
        dto.setPassword(this.usernameAndPassword);

        // when
        var result = assertThrows(UserAlreadyExistException.class,
                () -> userService.saveUser(dto));

        // then
        assertThat(result.getMessage()).isEqualTo(USER_ALREADY_EXISTS);

    }

    @Test
    void shouldVerifyIsUserWasDeleted() {
        // given
        UUID userId = initDatabaseByUser();
        AuthUserDto dto = new AuthUserDto();
        dto.setUsername(usernameAndPassword);
        dto.setId(userId);

        // when
        userService.deleteUser(dto);

        // then
        var user = userDetailsRepository.findByUsername(usernameAndPassword);
        assertThat(user.isEmpty()).isTrue();

    }

    @Test
    void shouldThrowKakeboDeleteUserExceptionWhenUserIsNotFoundInDatabase() {
        // given
        initDatabaseByUser();
        AuthUserDto dto = new AuthUserDto();
        dto.setUsername(usernameAndPassword);
        dto.setId(UUID.randomUUID());

        // when
        var result = assertThrows(KakeboDeleteUserException.class,
                () -> userService.deleteUser(dto));

        // then
        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(ExceptionMessages.USER_DELETE_ERROR);

    }

    @Test
    void shouldThrowKakeboDeleteUserExceptionWhenUserIsFoundInDatabaseAndHasDifferentName() {
        // given
        var userId = initDatabaseByUser();
        AuthUserDto dto = new AuthUserDto();
        dto.setUsername("fakeName");
        dto.setId(userId);

        // when
        var result = assertThrows(KakeboDeleteUserException.class,
                () -> userService.deleteUser(dto));

        // then
        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(ExceptionMessages.USER_DELETE_ERROR);

    }

    private UUID initDatabaseByUser() {
        AuthUserDto dto = new AuthUserDto();
        String usernameAndPassword = "user123";
        dto.setUsername(usernameAndPassword);
        dto.setPassword(usernameAndPassword);
        return userService.saveUser(dto);
    }
}