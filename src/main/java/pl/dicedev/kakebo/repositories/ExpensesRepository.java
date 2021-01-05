package pl.dicedev.kakebo.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;

import java.util.UUID;

public interface ExpensesRepository extends CrudRepository<ExpensesEntity, UUID> {
}
