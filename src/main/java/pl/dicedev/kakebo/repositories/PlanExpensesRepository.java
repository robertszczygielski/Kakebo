package pl.dicedev.kakebo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dicedev.kakebo.repositories.entities.PlanExpensesEntity;

import java.util.UUID;

public interface PlanExpensesRepository extends JpaRepository<PlanExpensesEntity, UUID> {
}
