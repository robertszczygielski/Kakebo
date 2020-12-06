package pl.dicedev.kakebo.controllers.handlers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakeboErrorDto {

    private String errorCode;
    private String errorMessage;
}
