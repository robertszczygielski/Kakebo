package pl.dicedev.kakebo.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class KakeboUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var userEntity = userDetailsRepository.findByUsername(userName);
        if (userEntity.isPresent()) {
            var ue = userEntity.get();
            return new User(ue.getUsername(), ue.getPassword(), Collections.emptyList());
        } else {
            return new User("NN", "NN", Collections.emptyList());
        }
    }
}
