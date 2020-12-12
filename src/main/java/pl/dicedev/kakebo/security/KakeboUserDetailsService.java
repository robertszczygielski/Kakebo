package pl.dicedev.kakebo.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.security.bto.UserBto;

import java.util.Collections;
import java.util.UUID;

@Service
@AllArgsConstructor
public class KakeboUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserBto loadUserByUsername(String userName) throws UsernameNotFoundException {
        var userEntity = userDetailsRepository.findByUsername(userName);
        if (userEntity.isPresent()) {
            var ue = userEntity.get();
            return UserBto.builder()
                    .username(ue.getUsername())
                    .password(ue.getPassword())
                    .authorities(Collections.emptyList())
                    .id(ue.getId())
                    .build();
        } else {
            return UserBto.builder()
                    .username("NN")
                    .password("NN")
                    .authorities(Collections.emptyList())
                    .id(UUID.randomUUID())
                    .build();

        }
    }
}
