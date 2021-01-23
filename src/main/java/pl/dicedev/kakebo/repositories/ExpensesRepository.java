package pl.dicedev.kakebo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.dicedev.kakebo.enums.ExpensesCategory;
import pl.dicedev.kakebo.repositories.entities.ExpensesEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ExpensesRepository extends CrudRepository<ExpensesEntity, UUID> {

    @Query("select sum(e.amount) from ExpensesEntity e where e.user = :user")
    BigDecimal countAllExpenses(@Param("user") UserEntity user);

    Optional<ExpensesEntity> findAllByUserAndExpensesCategory(UserEntity user, ExpensesCategory expensesCategory);

}
