package pl.dicedev.kakebo.security;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDetailsRepository userDetailsRepository;


    @Override
    public void saveUser(AuthUserDto authUserDto) {

    }
}
