package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.services.dtos.ExpensesPlanDto;

import java.util.List;

public interface ExpensesPlanService {

    void addExpensesPlan(ExpensesPlanDto expensesPlanDto);

    List<ExpensesPlanDto> getExpensesPlan();

    List<ExpensesPlanDto> getExpensesPlanByCategory(String category);
}
