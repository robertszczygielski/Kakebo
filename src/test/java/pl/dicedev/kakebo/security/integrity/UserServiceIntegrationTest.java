package pl.dicedev.kakebo.security.integrity;


import org.assertj.core.util.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.UserServiceImpl;
import pl.dicedev.kakebo.security.dto.AuthUserDto;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void shouldSaveOneUserInDatabaseWhenThereIsNoUser() {
        // given
        AuthUserDto dto = new AuthUserDto();
        String usernameAndPassword = "user123";
        dto.setUsername(usernameAndPassword);
        dto.setPassword(usernameAndPassword);

        // when
        userService.saveUser(dto);

        // then
        var allUsersIterable = userDetailsRepository.findAll();
        var allUsers = Streams.stream(allUsersIterable).collect(Collectors.toList());
        assertThat(allUsers).hasSize(1);
        assertThat(allUsers.get(0).getUsername()).isEqualTo(usernameAndPassword);

    }
}