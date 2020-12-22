package pl.dicedev.kakebo.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.exceptions.IncorrectUserOrPasswordException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.INCORRECT_USER_OR_PASSWORD;

@ExtendWith(MockitoExtension.class)
class KakeboUserDetailsServiceTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Test
    void shouldReturnUserBtoWhenUserIsExistInDatabase() {
        // given
        var userLogin = "admin@com.com";
        var id = UUID.randomUUID();
        var kakeboUserDetailsService = new KakeboUserDetailsService(userDetailsRepository);
        var entity = new UserEntity();
        entity.setId(id);
        Mockito.when(userDetailsRepository.findByUsername(userLogin)).thenReturn(Optional.of(entity));

        // when
        var result = kakeboUserDetailsService.loadUserByUsername(userLogin);

        // then
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void shouldThrowExceptionWhenUserNotExistInDatabase() {
        // given
        var userLogin = "admin@com.com";
        var kakeboUserDetailsService = new KakeboUserDetailsService(userDetailsRepository);
        Mockito.when(userDetailsRepository.findByUsername(userLogin)).thenReturn(Optional.empty());

        // when
        var result = Assertions.assertThrows(IncorrectUserOrPasswordException.class,
                () -> kakeboUserDetailsService.loadUserByUsername(userLogin));

        // then
        assertThat(result.getMessage()).isEqualTo(INCORRECT_USER_OR_PASSWORD);
    }
}