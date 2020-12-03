package pl.dicedev.kakebo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.util.JWTUtil;

import java.util.Collection;
import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {


    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private KakeboUserDetailsService userDetailsService;

    private JWTUtil jwtUtil;

    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        jwtUtil = new JWTUtil();

        authenticationService = new AuthenticationService(authenticationManager, userDetailsService, jwtUtil);
    }

    @Test
    void shouldReturnTokenWhenUserAndPasswordMatch() {
        // given
        String expectedTokenHeader = "eyJhbGciOiJIUzI1NiJ9";
        String username = "user";
        String password = "passwd";
        AuthUserDto authUserDto = new AuthUserDto(username, password);
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
}