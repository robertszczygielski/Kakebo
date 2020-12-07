package pl.dicedev.kakebo.security.exceptions;

public class BadKakeboCredentialsException extends RuntimeException {

    public BadKakeboCredentialsException() {
        super("User password or login are incorrect");
    }

}
