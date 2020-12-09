package pl.dicedev.kakebo.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.exceptions.BadKakeboCredentialsException;
import pl.dicedev.kakebo.security.util.JWTUtil;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.INCORRECT_USER_OR_PASSWORD;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {


    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private KakeboUserDetailsService userDetailsService;

    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        JWTUtil jwtUtil = new JWTUtil();

        authenticationService = new AuthenticationService(authenticationManager, userDetailsService, jwtUtil);
    }

    @Test
    void shouldReturnTokenWhenUserAndPasswordMatch() {
        // given
        String expectedTokenHeader = "eyJhbGciOiJIUzI1NiJ9";
        String username = "user";
        String password = "passwd";
        AuthUserDto authUserDto = new AuthUserDto(null, username, password);
        UserDetails userDetails = new User(username, password, Collections.emptyList());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        // when
        var result = authenticationService.authenticateUser(authUserDto);

        // then
        String resultHeader = result.getJwtToken().substring(0, 20);
        assertThat(resultHeader).isEqualTo(expectedTokenHeader);

    }

    @Test
    void shouldThrowBadKakeboCredentialsExceptionWhenAuthenticationFails() {
        // given
        String username = "user";
        String password = "bad_passwd";
        AuthUserDto authUserDto = new AuthUserDto(null, username, password);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        Mockito.when(authenticationManager.authenticate(auth)).thenThrow(BadCredentialsException.class);

        // when
        var badKakeboCredentialsException = Assertions.assertThrows(BadKakeboCredentialsException.class,
                () -> authenticationService.authenticateUser(authUserDto)

        );
        //then
        assertThat(badKakeboCredentialsException.getMessage()).isEqualTo(INCORRECT_USER_OR_PASSWORD);

    }
}