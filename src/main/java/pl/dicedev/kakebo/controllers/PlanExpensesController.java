package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dicedev.kakebo.services.PlanExpensesService;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

import java.util.List;

@RestController
@RequestMapping("/plan/expenses")
@AllArgsConstructor
public class PlanExpensesController {

    private final PlanExpensesService planExpensesService;

    public List<PlanExpensesDto> getPlanExpenses() {
        return planExpensesService.getPlanExpenses();
    }

    @PostMapping
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {
        planExpensesService.addPlanExpenses(planExpensesDto);
    }

}
