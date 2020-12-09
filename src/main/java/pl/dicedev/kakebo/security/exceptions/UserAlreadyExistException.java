package pl.dicedev.kakebo.security.exceptions;

import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.USER_ALREADY_EXISTS;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super(USER_ALREADY_EXISTS);
    }
}
