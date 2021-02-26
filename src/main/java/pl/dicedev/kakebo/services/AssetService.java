package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.List;
import java.util.UUID;

public interface AssetService {

    UUID save(AssetDto assetDto);

    AssetDto findById(UUID id);

    List<AssetDto> findAll();

    List<AssetDto> getAssetByCategory(String category);
}
