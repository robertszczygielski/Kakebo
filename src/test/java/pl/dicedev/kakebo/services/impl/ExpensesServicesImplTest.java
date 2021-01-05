package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.dicedev.kakebo.mappers.ExpensesMapper;
import pl.dicedev.kakebo.mappers.ExpensesSingleMapperImpl;
import pl.dicedev.kakebo.repositories.ExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.services.ExpensesService;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ExpensesServicesImplTest {

    private ExpensesService expensesService;

    @Mock
    private ExpensesRepository expensesRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @BeforeEach
    public void init() {
        var expensesSingleMapper = new ExpensesSingleMapperImpl();
        var expensesMapper = new ExpensesMapper(expensesSingleMapper);
        expensesService = new ExpensesServiceImpl(expensesRepository, expensesMapper, userDetailsRepository);
    }

    @Test
    void shouldCallSaveAllWithOneElement() {
        // given
        var userId = UUID.randomUUID();
        var expectedEntities = prepareEntities(1, userId);
        var dot = prepareDtos(1);
        var userBto = UserBto.builder().id(userId).build();
        var userEntity = new UserEntity();
        userEntity.setId(userId);

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userBto);
        Mockito.when(userDetailsRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // when
        expensesService.saveExpenses(dot);

        // then
        Mockito.verify(expensesRepository).saveAll(expectedEntities);
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
