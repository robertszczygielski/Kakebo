package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.security.exceptions.UserNotExistException;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.USER_NOT_EXISTS;

@ExtendWith(MockitoExtension.class)
class UserLogInfoServiceTest {

    private UserLogInfoService userLogInfoService;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    public void init() {
        userLogInfoService = new UserLogInfoService(userDetailsRepository);
    }

    @Test
    void shouldThrowExceptionWhenUserIsNotFound() {
        // given
        var userId = UUID.randomUUID();
        var userBto = UserBto.builder().id(userId).build();

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userBto);

        Mockito.when(userDetailsRepository.findById(userId)).thenReturn(Optional.empty());

        // when
        var result = Assertions.assertThrows(UserNotExistException.class,
                () -> userLogInfoService.getLoggedUserEntity());

        // then
        assertThat(result.getMessage()).isEqualTo(USER_NOT_EXISTS);
    }

    @Test
    void shouldReturnUserEntity() {
        // given
        var userId = UUID.randomUUID();
        var userBto = UserBto.builder().id(userId).build();

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userBto);

        Mockito.when(userDetailsRepository.findById(userId)).thenReturn(Optional.of(new UserEntity()));

        // when
        var result = userLogInfoService.getLoggedUserEntity();

        // then
        assertThat(result).isNotNull();

    }
}