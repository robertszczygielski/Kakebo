package pl.dicedev.kakebo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dicedev.kakebo.security.AuthenticationService;
import pl.dicedev.kakebo.security.UserService;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    public AuthResponse createAuthenticationToken(@RequestBody AuthUserDto authUserDto) {
        return authenticationService.authenticateUser(authUserDto);
    }

    @PostMapping("/createUser")
    public UUID createUser(@Valid @RequestBody AuthUserDto authUserDto) {
        return userService.saveUser(authUserDto);
    }


}