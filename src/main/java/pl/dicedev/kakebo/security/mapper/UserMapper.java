package pl.dicedev.kakebo.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.entity.UserEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    private final String SALT = "random_salt";

    public abstract UserEntity fromDtoToEntity(AuthUserDto authUserDto);

}
