package pl.dicedev.kakebo.repositories.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import pl.dicedev.kakebo.enums.ExpensesCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "expenses_plan")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ExpensesPlanEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "expense_date")
    private Instant expensesDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private ExpensesCategory expensesCategory;

}
