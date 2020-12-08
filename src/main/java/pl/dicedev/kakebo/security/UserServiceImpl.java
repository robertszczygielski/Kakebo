package pl.dicedev.kakebo.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.mapper.UserMapper;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserMapper userMapper;


    @Override
    public UUID saveUser(AuthUserDto authUserDto) {
        log.info("dto user = {}", authUserDto);
        var userEntity = userMapper.fromDtoToEntity(authUserDto);
        log.info("user: {}", userEntity);
        var userEntityAfterSave = userDetailsRepository.save(userEntity);
        return userEntityAfterSave.getId();
    }
}
