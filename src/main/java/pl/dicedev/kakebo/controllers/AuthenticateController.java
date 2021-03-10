package pl.dicedev.kakebo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.controllers.handlers.dto.KakeboErrorDto;
import pl.dicedev.kakebo.security.UserService;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.services.LoginService;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticateController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping
    public AuthResponse createAuthenticationToken(@RequestBody AuthUserDto authUserDto) {
        return loginService.loginUser(authUserDto);
    }

    @PostMapping("/logout")
    public HttpStatus logoutUser() {
        loginService.logoutUser();
        return HttpStatus.NO_CONTENT;
    }

    @PostMapping("/user/create")
    public UUID createUser(@Valid @RequestBody AuthUserDto authUserDto) {
        return userService.saveUser(authUserDto);
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@Valid @RequestBody AuthUserDto authUserDto) {
        userService.deleteUser(authUserDto);
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