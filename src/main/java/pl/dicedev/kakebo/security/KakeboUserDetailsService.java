package pl.dicedev.kakebo.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.security.exceptions.IncorrectUserOrPasswordException;

import java.util.Collections;

@Service
@AllArgsConstructor
public class KakeboUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserBto loadUserByUsername(String userName) throws UsernameNotFoundException {
        var userEntity = this.userDetailsRepository.findByUsername(userName);
        if (userEntity.isPresent()) {
            var ue = userEntity.get();
            return UserBto.builder()
                    .username(ue.getUsername())
                    .password(ue.getPassword())
                    .authorities(Collections.emptyList())
                    .logged(ue.isLogged())
                    .id(ue.getId())
                    .build();
        } else {
            throw new IncorrectUserOrPasswordException();
        }
    }
}
