package pl.dicedev.kakebo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthUserDto {

    @Email(message = "Username should be an email")
    private String username;
    private String password;

}
