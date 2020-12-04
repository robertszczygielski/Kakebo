package pl.dicedev.kakebo.security;

import pl.dicedev.kakebo.security.dto.AuthUserDto;

public interface UserService {

    void saveUser(AuthUserDto authUserDto);

}
