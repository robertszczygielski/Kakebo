package pl.dicedev.kakebo.security.exceptions;

import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.INCORRECT_USER_OR_PASSWORD;

public class UserNotExistInDatabaseException extends RuntimeException {

    public UserNotExistInDatabaseException() {
        super(INCORRECT_USER_OR_PASSWORD);
    }
}
