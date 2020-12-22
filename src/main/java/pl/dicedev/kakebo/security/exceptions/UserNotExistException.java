package pl.dicedev.kakebo.security.exceptions;

import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.USER_NOT_EXISTS;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super(USER_NOT_EXISTS);
    }

}
