package pl.dicedev.kakebo.security.bto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBto implements UserDetails {

    @NotNull
    private String password;
    @NotNull
    private String username;
    @NotNull
    private UUID id;
    @NotNull
    private Collection<? extends GrantedAuthority> authorities;
    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;

}
