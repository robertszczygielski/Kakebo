package pl.dicedev.kakebo.security.exceptions;

import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.INCORRECT_USER_OR_PASSWORD;

public class BadKakeboCredentialsException extends RuntimeException {

    public BadKakeboCredentialsException() {
        super(INCORRECT_USER_OR_PASSWORD);
    }

}
