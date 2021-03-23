package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.mappers.ExpensesPlanMapper;
import pl.dicedev.kakebo.mappers.ExpensesPlanMapperImpl;
import pl.dicedev.kakebo.repositories.ExpensesPlanRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesPlanEntity;
import pl.dicedev.kakebo.services.ExpensesPlanService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void shouldGetAllExpensesPlan() {
        // given
        List<ExpensesPlanEntity> allEntities = Collections.singletonList(new ExpensesPlanEntity());
        when(expensesPlanRepository.findAll()).thenReturn(allEntities);

        // when
        var result = expensesPlanService.getExpensesPlan();

        // then
        assertThat(result.size()).isEqualTo(1);

    }
}