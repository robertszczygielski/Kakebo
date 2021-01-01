package pl.dicedev.kakebo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthUserDto {

    private UUID id;
    @Email(message = "Username should be an email")
    private String username;
    private String password;

}
