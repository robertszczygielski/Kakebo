package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.security.exceptions.UserNotExistException;

@AllArgsConstructor
@Component
public class UserLogInfoService {

    private final UserDetailsRepository userDetailsRepository;

    public UserEntity getLoggedUserEntity() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userId = ((UserBto) auth.getPrincipal()).getId();
        var userEntityOptional = userDetailsRepository.findById(userId);

        return userEntityOptional.orElseThrow(UserNotExistException::new);
    }

}
