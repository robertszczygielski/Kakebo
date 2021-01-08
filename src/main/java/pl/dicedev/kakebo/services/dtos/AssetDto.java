package pl.dicedev.kakebo.services.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class AssetDto {

    private UUID id;
    private BigDecimal amount;
    private Instant incomeDate;

}
