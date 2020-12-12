package pl.dicedev.kakebo.security.bto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Data
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
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
