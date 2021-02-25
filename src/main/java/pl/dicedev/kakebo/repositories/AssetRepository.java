package pl.dicedev.kakebo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dicedev.kakebo.enums.AssetCategory;
import pl.dicedev.kakebo.repositories.entities.AssetEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetRepository extends CrudRepository<AssetEntity, UUID> {

    List<AssetEntity> getAssetEntitiesByAssetCategory(AssetCategory assetCategory);

}
