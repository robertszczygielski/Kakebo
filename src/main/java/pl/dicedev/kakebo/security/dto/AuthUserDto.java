package pl.dicedev.kakebo.security.dto;

import lombok.*;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthUserDto {

    @Email(message = "Username should be an email")
    private String username;
    private String password;

}
