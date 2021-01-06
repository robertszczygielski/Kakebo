package pl.dicedev.kakebo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpensesController {

    private final ExpensesService expensesService;

    public void setExpenses(List<ExpensesDto> expenses) {

    }

}
