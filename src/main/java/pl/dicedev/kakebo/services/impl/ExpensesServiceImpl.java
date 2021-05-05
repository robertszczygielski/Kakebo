package pl.dicedev.kakebo.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.annotations.LogDebug;
import pl.dicedev.kakebo.annotations.LogInfo;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.mappers.ExpensesMapper;
import pl.dicedev.kakebo.repositories.ExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper expensesMapper;
    private final UserLogInfoService userLogInfoService;

    @Override
    @LogDebug
    public void saveExpenses(List<ExpensesDto> expensesDtos) {
        log.info("Expenses to save: {}", expensesDtos.size());
        var expensesEntities = expensesMapper.fromDtosToEntities(expensesDtos, getLoggedUserEntity());
        expensesRepository.saveAll(expensesEntities);
        log.info("Saved expenses = {}", expensesEntities.size());
    }

    @Override
    @LogInfo
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

    @Override
    public List<ExpensesDto> getAllExpenses() {
        var allExpenses = expensesRepository.findAll();
        var expensesList = StreamSupport.stream(allExpenses.spliterator(), false)
                .collect(Collectors.toList());
        return expensesMapper.fromEntitiesToDtos(expensesList);
    }

    private UserEntity getLoggedUserEntity() {
        return userLogInfoService.getLoggedUserEntity();
    }

}
