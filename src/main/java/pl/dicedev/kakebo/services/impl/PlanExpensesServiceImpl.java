package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.mappers.PlanExpensesMapper;
import pl.dicedev.kakebo.repositories.PlanExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.PlanExpensesEntity;
import pl.dicedev.kakebo.services.PlanExpensesService;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanExpensesServiceImpl implements PlanExpensesService {

    private final PlanExpensesRepository planExpensesRepository;
    private final PlanExpensesMapper planExpensesMapper;

    @Override
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {
        PlanExpensesEntity planExpensesEntity = planExpensesMapper.fromDtoToEntities(planExpensesDto);

        planExpensesRepository.save(planExpensesEntity);
    }

    @Override
    public List<PlanExpensesDto> getPlanExpenses() {
        return null;
    }
}
