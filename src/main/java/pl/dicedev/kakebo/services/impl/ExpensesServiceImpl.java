package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.mappers.ExpensesMapper;
import pl.dicedev.kakebo.repositories.ExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.security.exceptions.UserNotExistException;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExpensesServiceImpl implements ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper expensesMapper;
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public void saveExpenses(List<ExpensesDto> expensesDtos) {
        log.info("Expenses to save: {}", expensesDtos.size());
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userId = ((UserBto) auth.getPrincipal()).getId();
        var userEntityOptional = userDetailsRepository.findById(userId);
        UserEntity userEntity;
        if (userEntityOptional.isPresent()) {
            userEntity = userEntityOptional.get();
        } else {
            log.error("NO USER");
            throw new UserNotExistException();
        }

        var expensesEntities = expensesMapper.fromDtosToEntities(expensesDtos, userEntity);
        expensesRepository.saveAll(expensesEntities);
        log.info("Saved expenses = {}", expensesEntities.size());
    }

}
