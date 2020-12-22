package pl.dicedev.kakebo.security.exceptions;

import static pl.dicedev.kakebo.security.exceptions.ExceptionMessages.INCORRECT_USER_OR_PASSWORD;

public class IncorrectUserOrPasswordException extends RuntimeException {

    public IncorrectUserOrPasswordException() {
        super(INCORRECT_USER_OR_PASSWORD);
    }
}
