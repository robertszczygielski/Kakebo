package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.annotations.LogInfo;
import pl.dicedev.kakebo.security.AuthenticationService;
import pl.dicedev.kakebo.security.UserService;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.services.LoginService;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Override
    @LogInfo
    public AuthResponse loginUser(AuthUserDto authUserDto) {
        var token = authenticationService.authenticateUser(authUserDto);
        userService.setLoggedIn(authUserDto.getUsername());
        return token;
    }

    @Override
    public void logoutUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userId = ((UserBto) auth.getPrincipal()).getId();
        userService.setLoggedOut(userId);
    }
}
