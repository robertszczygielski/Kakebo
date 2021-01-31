package pl.dicedev.kakebo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

@RestController
@RequestMapping("/plan/expenses")
public class PlanExpensesController {

    @PostMapping
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {

    }

}
