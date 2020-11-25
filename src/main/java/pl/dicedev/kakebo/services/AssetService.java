package pl.dicedev.kakebo.services;

import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.UUID;

public interface AssetService {

    UUID save(AssetDto assetDto);

    AssetDto findById(UUID id);

    AssetDto findFirst();

}
