package pl.dicedev.kakebo.services.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.security.AuthenticationService;
import pl.dicedev.kakebo.security.UserService;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private UserService userService;

    private LoginServiceImpl loginService;

    @BeforeEach
    public void setup() {
        loginService = new LoginServiceImpl(userService, authenticationService);
    }

    @Test
    void shouldVerifyIfUserServiceLoggedInByUsernameIsCalled() {
        // given
        var username = "user";
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUsername(username);
        AuthResponse token = new AuthResponse("token");
        when(authenticationService.authenticateUser(authUserDto)).thenReturn(token);
        doNothing().when(userService).setLoggedIn(username);

        // when
        loginService.loginUser(authUserDto);

        // then
        verify(userService).setLoggedIn(username);

    }
}