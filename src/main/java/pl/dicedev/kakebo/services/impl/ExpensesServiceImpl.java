package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.mappers.ExpensesMapper;
import pl.dicedev.kakebo.repositories.ExpensesRepository;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

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
        var expensesEntities = expensesMapper.fromDtosToEntities(expensesDtos, userLogInfoService.getLoggedUserEntity());
        expensesRepository.saveAll(expensesEntities);
        log.info("Saved expenses = {}", expensesEntities.size());
    }

}
