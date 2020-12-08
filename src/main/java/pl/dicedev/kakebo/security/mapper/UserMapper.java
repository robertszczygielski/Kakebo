package pl.dicedev.kakebo.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.entity.UserEntity;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    private final String SALT = "$2a$06$rECVOz6Vt9ALftZLmFM63.";

    @Mappings({
            @Mapping(source = "password", target = "password", qualifiedByName = "hashPasswd"),
            @Mapping(source = "username", target = "username")
    })
    public abstract UserEntity fromDtoToEntity(AuthUserDto authUserDto);

    @Named("hashPasswd")
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, SALT);
    }

}
