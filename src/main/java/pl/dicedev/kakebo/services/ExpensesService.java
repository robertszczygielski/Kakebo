package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.List;

public interface ExpensesService {

    void saveExpenses(List<ExpensesDto> expensesDtos);

    BigDecimal countAllExpenses();

}
