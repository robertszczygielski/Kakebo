package pl.dicedev.kakebo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.security.dto.AuthResponse;
import pl.dicedev.kakebo.security.dto.AuthUserDto;
import pl.dicedev.kakebo.security.exceptions.BadKakeboCredentialsException;
import pl.dicedev.kakebo.security.util.JWTUtil;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final KakeboUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;


    public AuthResponse authenticateUser(AuthUserDto authUserDto) throws RuntimeException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authUserDto.getUsername(), authUserDto.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadKakeboCredentialsException();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authUserDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthResponse(jwt);

    }
}
