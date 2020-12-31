package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

import java.util.UUID;

public interface LoginService {

    AuthResponse loginUser(AuthUserDto authUserDto);

    void logoutUser(UUID uuid);

}
