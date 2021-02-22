package pl.dicedev.kakebo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.dtos.ExpensesDto;

@Mapper(componentModel = "spring")
public interface ExpensesSingleMapper {

    @Mapping(source = "userEntity", target = "user")
    @Mapping(source = "dto.amount", target = "amount")
    @Mapping(source = "dto.expensesDate", target = "expensesDate")
    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.expensesCategory", target = "expensesCategory")
    ExpensesEntity fromDtoToEntities(ExpensesDto dto, UserEntity userEntity);

    ExpensesDto formEntitiesToDto(ExpensesEntity entity);

}
