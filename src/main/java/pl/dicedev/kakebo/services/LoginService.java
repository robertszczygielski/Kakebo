package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

public interface LoginService {

    AuthResponse loginUser(AuthUserDto authUserDto);

    void logoutUser();

}
