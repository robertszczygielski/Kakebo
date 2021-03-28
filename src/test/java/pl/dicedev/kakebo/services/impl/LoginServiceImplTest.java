package pl.dicedev.kakebo.services.impl;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.dicedev.kakebo.security.AuthenticationService;
import pl.dicedev.kakebo.security.UserService;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

import java.util.UUID;

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

    @AfterEach
    public void clearSecurityContext() {
        SecurityContextHolder.clearContext();
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

    @Test
    void shouldVerifyIfUserWasLogout() {
        // given
        UUID userId = UUID.randomUUID();
        UserBto mockUserBto = UserBto.builder().id(userId).build();
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockUserBto);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // when
        loginService.logoutUser();

        // then
        verify(userService).setLoggedOut(userId);

    }
}