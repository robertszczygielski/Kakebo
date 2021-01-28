package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    @PostMapping
    public void setExpenses(@RequestBody List<ExpensesDto> expenses) {
        expensesService.saveExpenses(expenses);
    }

    @GetMapping("/counted")
    public BigDecimal getCountedExpenses() {
        return expensesService.countAllExpenses();
    }

    @GetMapping("/categories")
    public List<ExpensesCategory> getAllExpensesCategories() {
        return asList(ExpensesCategory.values());
    }

    @GetMapping("/counted/category")
    public BigDecimal getCountedExpensesByCategory(@RequestBody String category) {
        return expensesService.countExpensesForCategory(
                ExpensesCategory.valueOf(category.toUpperCase())
        );
    }

}
