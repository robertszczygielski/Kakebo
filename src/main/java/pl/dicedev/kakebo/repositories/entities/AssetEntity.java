package pl.dicedev.kakebo.repositories.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import pl.dicedev.kakebo.enums.AssetCategory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "asset")
@Data
public class AssetEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private Instant incomeDate;

    @Enumerated(EnumType.STRING)
    private AssetCategory assetCategory;

}
