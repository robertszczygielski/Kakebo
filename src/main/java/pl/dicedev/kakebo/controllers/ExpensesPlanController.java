package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.services.ExpensesPlanService;
import pl.dicedev.kakebo.services.dtos.ExpensesPlanDto;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/expenses/plan")
@AllArgsConstructor
public class ExpensesPlanController {

    private final ExpensesPlanService expensesPlanService;

    @GetMapping
    public List<ExpensesPlanDto> getExpensesPlan() {
        return expensesPlanService.getExpensesPlan();
    }

    @GetMapping("/find")
    public  List<ExpensesPlanDto> getExpensesPlanByCategory(@PathParam("category") String category) {
        return expensesPlanService.getExpensesPlanByCategory(category);
    }

    @PostMapping
    public void addExpensesPlan(ExpensesPlanDto expensesPlanDto) {
        expensesPlanService.addExpensesPlan(expensesPlanDto);
    }

}
