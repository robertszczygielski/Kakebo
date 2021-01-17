package pl.dicedev.kakebo.services.dtos;

import lombok.Data;
import pl.dicedev.kakebo.enums.ExpensesCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class ExpensesDto {

    private UUID id;
    private BigDecimal amount;
    private Instant expensesDate;
    private ExpensesCategory expensesCategory;

}
