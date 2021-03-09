package pl.dicedev.kakebo.services.dtos;

import lombok.Data;
import pl.dicedev.kakebo.enums.ExpensesCategory;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ExpensesPlanDto {

    private UUID id;
    private ExpensesCategory expensesCategory;
    private BigDecimal amount;

}
