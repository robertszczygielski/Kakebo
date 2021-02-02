package pl.dicedev.kakebo.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlanExpensesRepository extends CrudRepository<PlanExpensesRepository, UUID> {
}
