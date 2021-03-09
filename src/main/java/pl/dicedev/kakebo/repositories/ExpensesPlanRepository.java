package pl.dicedev.kakebo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesPlanEntity;

import java.util.UUID;

public interface ExpensesPlanRepository extends JpaRepository<ExpensesPlanEntity, UUID> {
}
