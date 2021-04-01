package pl.dicedev.kakebo.security.exceptions;

public class KakeboDeleteUserException extends RuntimeException {

    public KakeboDeleteUserException() {
        super(ExceptionMessages.USER_DELETE_ERROR);
    }
}
