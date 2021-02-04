package pl.dicedev.kakebo.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.dicedev.kakebo.repositories.entities.PlanExpensesEntity;

import java.util.UUID;

public interface PlanExpensesRepository extends CrudRepository<PlanExpensesEntity, UUID> {
}
