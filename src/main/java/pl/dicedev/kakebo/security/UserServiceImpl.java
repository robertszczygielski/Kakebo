package pl.dicedev.kakebo.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.exceptions.UserAlreadyExistException;
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
        var userInDataBase = userDetailsRepository.findByUsername(authUserDto.getUsername());
        if (userInDataBase.isEmpty()) {
            var userEntity = userMapper.fromDtoToEntity(authUserDto);
            log.info("user: {}", userEntity);
            var userEntityAfterSave = userDetailsRepository.save(userEntity);
            return userEntityAfterSave.getId();
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public void deleteUser(AuthUserDto authUserDto) {
        var userEntity = userDetailsRepository.findById(authUserDto.getId());
        if (userEntity.isPresent() && authUserDto.getUsername().equals(userEntity.get().getUsername())) {
            userDetailsRepository.delete(userEntity.get());
            log.info("user deleted successfully, {}", authUserDto.getUsername());
        } else {
            log.info("user do not exist; id:{}, username: {}", authUserDto.getId(), authUserDto.getUsername());
        }
    }
}
