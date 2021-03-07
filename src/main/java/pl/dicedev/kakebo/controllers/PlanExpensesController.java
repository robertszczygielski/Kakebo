package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.services.PlanExpensesService;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/expenses/plan")
@AllArgsConstructor
public class PlanExpensesController {

    private final PlanExpensesService planExpensesService;

    @GetMapping
    public List<PlanExpensesDto> getPlanExpenses() {
        return planExpensesService.getPlanExpenses();
    }

    @GetMapping("/find")
    public  List<PlanExpensesDto> getPlanExpensesByCategory(@PathParam("category") String category) {
        return planExpensesService.getPlanExpensesByCategory(category);
    }

    @PostMapping
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {
        planExpensesService.addPlanExpenses(planExpensesDto);
    }

}
