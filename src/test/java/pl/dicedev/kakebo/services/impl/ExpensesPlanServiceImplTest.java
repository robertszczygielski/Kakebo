package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.mappers.ExpensesPlanMapper;
import pl.dicedev.kakebo.mappers.ExpensesPlanMapperImpl;
import pl.dicedev.kakebo.repositories.ExpensesPlanRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesPlanEntity;
import pl.dicedev.kakebo.services.ExpensesPlanService;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpensesPlanServiceImplTest {

    @Mock
    private ExpensesPlanRepository expensesPlanRepository;

    private ExpensesPlanService expensesPlanService;

    @BeforeEach
    public void init() {
        ExpensesPlanMapper expensesPlanMapper = new ExpensesPlanMapperImpl();
        expensesPlanService = new ExpensesPlanServiceImpl(expensesPlanRepository, expensesPlanMapper);
    }

    @Test
    void shouldGetOneExpensesPlanIfInDatabaseIsOnePlane() {
        // given
        List<ExpensesPlanEntity> allEntities = Collections.singletonList(new ExpensesPlanEntity());
        when(expensesPlanRepository.findAll()).thenReturn(allEntities);

        // when
        var result = expensesPlanService.getExpensesPlan();

        // then
        assertThat(result).hasSize(1);

    }

    @Test
    void shouldReturnOneExpensesPlanByCategory() {
        // given
        var category = ExpensesCategory.FOR_LIVE.name();
        List<ExpensesPlanEntity> allEntities = prepareExpensesPlanEntities();

        when(expensesPlanRepository.findAll()).thenReturn(allEntities);

        // when
        var result = expensesPlanService.getExpensesPlanByCategory(category);

        // then
        assertThat(result).hasSize(1);
    }

    private List<ExpensesPlanEntity> prepareExpensesPlanEntities() {
        var expensesPlanEntity1 = new ExpensesPlanEntity();
        expensesPlanEntity1.setExpensesCategory(ExpensesCategory.FOR_LIVE);

        var expensesPlanEntity2 = new ExpensesPlanEntity();
        expensesPlanEntity2.setExpensesCategory(ExpensesCategory.OTHER);

        return asList(expensesPlanEntity1, expensesPlanEntity2);
    }
}