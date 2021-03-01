package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dicedev.kakebo.services.PlanExpensesService;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

@RestController
@RequestMapping("/plan/expenses")
@AllArgsConstructor
public class PlanExpensesController {

    private final PlanExpensesService planExpensesService;

    @PostMapping
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {
        planExpensesService.addPlanExpenses(planExpensesDto);
    }

}
