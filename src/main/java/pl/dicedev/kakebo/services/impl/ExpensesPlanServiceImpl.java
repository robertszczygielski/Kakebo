package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.annotations.LogDebug;
import pl.dicedev.kakebo.annotations.LogInfo;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.mappers.ExpensesPlanMapper;
import pl.dicedev.kakebo.repositories.ExpensesPlanRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesPlanEntity;
import pl.dicedev.kakebo.services.ExpensesPlanService;
import pl.dicedev.kakebo.services.dtos.ExpensesPlanDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpensesPlanServiceImpl implements ExpensesPlanService {

    private final ExpensesPlanRepository expensesPlanRepository;
    private final ExpensesPlanMapper expensesPlanMapper;

    @Override
    @LogInfo
    @LogDebug
    public void addExpensesPlan(ExpensesPlanDto expensesPlanDto) {
        ExpensesPlanEntity expensesPlanEntity = expensesPlanMapper.fromDtoToEntities(expensesPlanDto);

        expensesPlanRepository.save(expensesPlanEntity);
    }

    @Override
    @LogInfo
    public List<ExpensesPlanDto> getExpensesPlan() {
        return expensesPlanRepository.findAll()
                .stream()
                .map(expensesPlanMapper::fromEntityToDto)
                .collect(Collectors.toList());

    }

    @Override
    @LogInfo
    public List<ExpensesPlanDto> getExpensesPlanByCategory(String category) {
        var expenseCategory = ExpensesCategory.valueOf(category);
        var entities = expensesPlanRepository.findAll().stream()
                .filter(entity -> expenseCategory.equals(entity.getExpensesCategory()))
                .collect(Collectors.toList());

        return entities.stream()
                .map(expensesPlanMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
}
