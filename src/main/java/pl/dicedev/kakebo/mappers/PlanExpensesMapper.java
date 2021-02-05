package pl.dicedev.kakebo.mappers;

import org.mapstruct.Mapper;
import pl.dicedev.kakebo.repositories.entities.PlanExpensesEntity;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

@Mapper(componentModel = "spring")
public interface PlanExpensesMapper {

    PlanExpensesEntity fromDtoToEntities(PlanExpensesDto dto);

}
