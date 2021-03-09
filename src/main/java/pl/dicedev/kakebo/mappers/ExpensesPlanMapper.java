package pl.dicedev.kakebo.mappers;

import org.mapstruct.Mapper;
import pl.dicedev.kakebo.repositories.entities.ExpensesPlanEntity;
import pl.dicedev.kakebo.services.dtos.ExpensesPlanDto;

@Mapper(componentModel = "spring")
public interface ExpensesPlanMapper {

    ExpensesPlanEntity fromDtoToEntities(ExpensesPlanDto dto);

    ExpensesPlanDto fromEntityToDto(ExpensesPlanEntity entity);
}
