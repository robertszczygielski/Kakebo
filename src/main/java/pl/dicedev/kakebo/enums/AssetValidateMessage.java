package pl.dicedev.kakebo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AssetValidateMessage {

    NO_ASSET("Amount is required");

    private final String message;

}
