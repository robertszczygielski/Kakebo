package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.services.PlanExpensesService;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/plan/expenses")
@AllArgsConstructor
public class PlanExpensesController {

    private final PlanExpensesService planExpensesService;

    @GetMapping
    public List<PlanExpensesDto> getPlanExpenses() {
        return planExpensesService.getPlanExpenses();
    }

    @GetMapping
    public  List<PlanExpensesDto> getPlanExpensesByCategory(@PathParam("category") String category) {
        // return planExpensesService.getPlanExpensesByCategory();
        return null;
    }

    @PostMapping
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {
        planExpensesService.addPlanExpenses(planExpensesDto);
    }

}
