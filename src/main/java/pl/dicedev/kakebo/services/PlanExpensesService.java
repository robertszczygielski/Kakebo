package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

import java.util.List;

public interface PlanExpensesService {

    void addPlanExpenses(PlanExpensesDto planExpensesDto);

    List<PlanExpensesDto>  getPlanExpenses();

}
