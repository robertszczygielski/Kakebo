package pl.dicedev.kakebo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.controllers.handlers.dto.KakeboErrorDto;
import pl.dicedev.kakebo.security.AuthenticationService;
import pl.dicedev.kakebo.security.UserService;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

import javax.validation.Valid;
import java.util.Objects;
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

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public KakeboErrorDto handleException(MethodArgumentNotValidException exception) {
        return KakeboErrorDto.builder()
                .errorMessage(exception.getMessage())
                .errorCode(Objects.requireNonNull(exception.getFieldError()).getCode())
                .build();
    }


}