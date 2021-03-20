package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    @GetMapping
    public List<ExpensesDto> getAllExpenses() {
        return expensesService.getAllExpenses();
    }

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

    @GetMapping("/find")
    public List<ExpensesDto> getExpensesByCategory(@PathParam("category") String category) {
        return expensesService.getExpensesByCategory(category);
    }

}
