package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    @PostMapping
    public void setExpenses(@RequestBody List<ExpensesDto> expenses) {
        expensesService.saveExpenses(expenses);
    }

    @GetMapping
    public BigDecimal getCountedExpenses() {
        return expensesService.countAllExpenses();
    }

}
