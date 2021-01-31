package pl.dicedev.kakebo.services.dtos;

import pl.dicedev.kakebo.enums.ExpensesCategory;

import java.math.BigDecimal;
import java.util.UUID;

public class PlanExpensesDto {

    private UUID id;
    private ExpensesCategory expensesCategory;
    private BigDecimal amount;

}
