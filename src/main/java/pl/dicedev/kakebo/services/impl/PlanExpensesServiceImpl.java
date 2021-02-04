package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.repositories.PlanExpensesRepository;
import pl.dicedev.kakebo.repositories.entities.PlanExpensesEntity;
import pl.dicedev.kakebo.services.PlanExpensesService;
import pl.dicedev.kakebo.services.dtos.PlanExpensesDto;

@Service
@AllArgsConstructor
public class PlanExpensesServiceImpl implements PlanExpensesService {

    private final PlanExpensesRepository planExpensesRepository;

    @Override
    public void addPlanExpenses(PlanExpensesDto planExpensesDto) {
        PlanExpensesEntity planExpensesEntity = new PlanExpensesEntity();

        planExpensesRepository.save(planExpensesEntity);
    }
}
