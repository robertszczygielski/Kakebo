package pl.dicedev.kakebo.validators;

import org.springframework.stereotype.Component;
import pl.dicedev.kakebo.annotations.LogInfo;
import pl.dicedev.kakebo.enums.AssetValidateMessage;
import pl.dicedev.kakebo.exceptions.AssetValidationException;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.Objects;

@Component
public class AssetValidator {

    @LogInfo
    public void valid(AssetDto dto) {
        if (Objects.isNull(dto.getAmount())) {
            throw new AssetValidationException(AssetValidateMessage.NO_ASSET.getMessage());
        }
    }
}
