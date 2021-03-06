package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.mappers.ExpensesMapper;
import pl.dicedev.kakebo.mappers.ExpensesSingleMapperImpl;
import pl.dicedev.kakebo.repositories.ExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpensesServicesImplTest {

    private ExpensesService expensesService;

    @Mock
    private ExpensesRepository expensesRepository;

    @Mock
    private UserLogInfoService userLogInfoService;

    @BeforeEach
    public void init() {
        var expensesSingleMapper = new ExpensesSingleMapperImpl();
        var expensesMapper = new ExpensesMapper(expensesSingleMapper);
        expensesService = new ExpensesServiceImpl(expensesRepository, expensesMapper, userLogInfoService);
    }

    @Test
    void shouldCallSaveAllWithOneElement() {
        // given
        var userId = UUID.randomUUID();
        var expectedEntities = prepareEntities(1, userId);
        var dot = prepareDtos(1);
        var userEntity = new UserEntity();
        userEntity.setId(userId);

        Mockito.when(userLogInfoService.getLoggedUserEntity()).thenReturn(userEntity);

        // when
        expensesService.saveExpenses(dot);

        // then
        Mockito.verify(expensesRepository).saveAll(expectedEntities);
    }

    @Test
    void shouldReturnDtoListMappedFromAllExpensesFindInDatabase() {
        // given
        UUID expensesId = UUID.randomUUID();
        ExpensesEntity entity = new ExpensesEntity();
        entity.setAmount(BigDecimal.ONE);
        entity.setId(expensesId);

        List<ExpensesEntity> entitiesList = Collections.singletonList(entity);
        when(expensesRepository.findAll()).thenReturn(entitiesList);

        // when
        var result = expensesService.getAllExpenses();

        // then
        assertThat(result).isNotNull().hasSize(1);
        var dto = result.get(0);
        assertThat(dto.getId()).isEqualTo(expensesId);
    }

    @Test
    void shouldReturnListsOfDtosParsedByMapper() {
        // given
        var category = ExpensesCategory.FOR_LIVE.name();

        UUID entityId = UUID.randomUUID();
        ExpensesEntity entity = new ExpensesEntity();
        entity.setId(entityId);
        List<ExpensesEntity> entityList = Collections.singletonList(entity);
        when(expensesRepository.findExpensesEntitiesByExpensesCategory(ExpensesCategory.FOR_LIVE)).thenReturn(entityList);

        // when
        var result = expensesService.getExpensesByCategory(category);

        // then
        assertThat(result).hasSize(1);
        var dto = result.get(0);
        assertThat(dto.getId()).isEqualTo(entityId);

    }

    private List<ExpensesDto> prepareDtos(int numberOfDtos) {
        return IntStream.range(0, numberOfDtos)
                .mapToObj(it -> {
                    var dot = new ExpensesDto();
                    dot.setAmount(new BigDecimal(it));
                    return dot;
                }).collect(Collectors.toList());
    }

    private List<ExpensesEntity> prepareEntities(int numberOfEntities, UUID userId) {
        var user = new UserEntity();
        user.setId(userId);

        return IntStream.range(0, numberOfEntities)
                .mapToObj(it -> {
                    var entity = new ExpensesEntity();
                    entity.setUser(user);
                    entity.setAmount(new BigDecimal(it));
                    return entity;
                }).collect(Collectors.toList());
    }
}
