package pl.dicedev.kakebo.security;

import pl.dicedev.kakebo.security.dto.AuthUserDto;

import java.util.UUID;

public interface UserService {

    UUID saveUser(AuthUserDto authUserDto);

    void deleteUser(AuthUserDto authUserDto);
}
