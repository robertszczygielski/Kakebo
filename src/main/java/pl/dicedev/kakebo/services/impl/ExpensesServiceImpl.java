package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.mappers.ExpensesMapper;
import pl.dicedev.kakebo.mappers.ExpensesSingleMapper;
import pl.dicedev.kakebo.repositories.ExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper expensesMapper;
    private final UserLogInfoService userLogInfoService;

    @Override
    public void saveExpenses(List<ExpensesDto> expensesDtos) {
        log.info("Expenses to save: {}", expensesDtos.size());
        var expensesEntities = expensesMapper.fromDtosToEntities(expensesDtos, getLoggedUserEntity());
        expensesRepository.saveAll(expensesEntities);
        log.info("Saved expenses = {}", expensesEntities.size());
    }

    @Override
    public BigDecimal countAllExpenses() {
        var loggedUser = getLoggedUserEntity();
        return expensesRepository.countAllExpenses(loggedUser);
    }

    @Override
    public BigDecimal countExpensesForCategory(ExpensesCategory expensesCategory) {
        return expensesRepository.countByUserAndExpensesCategory(getLoggedUserEntity(), expensesCategory);
    }

    @Override
    public List<ExpensesDto> getExpensesByCategory(String category) {
        List<ExpensesEntity> entities = expensesRepository.findExpensesEntitiesByExpensesCategory(ExpensesCategory.valueOf(category.toUpperCase()));
        return expensesMapper.fromEntitiesToDtos(entities);
    }

    private UserEntity getLoggedUserEntity() {
        return userLogInfoService.getLoggedUserEntity();
    }

}
