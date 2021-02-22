package pl.dicedev.kakebo.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ExpensesMapper {

    private final ExpensesSingleMapper expensesSingleMapper;

    public List<ExpensesEntity> fromDtosToEntities(List<ExpensesDto> dtos, UserEntity userEntity) {
        return dtos.stream()
                .map(it -> expensesSingleMapper.fromDtoToEntities(it, userEntity))
                .collect(Collectors.toList());
    }

    public List<ExpensesDto> fromEntitiesToDtos(List<ExpensesEntity> entities) {
        return entities.stream()
                .map(expensesSingleMapper::formEntitiesToDto)
                .collect(Collectors.toList());
    }
}
