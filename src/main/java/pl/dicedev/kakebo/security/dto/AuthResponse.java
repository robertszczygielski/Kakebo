package pl.dicedev.kakebo.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthResponse {

    private final String jwtToken;

}
