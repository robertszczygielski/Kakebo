package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.mappers.AssetMapper;
import pl.dicedev.kakebo.repositories.AssetRepository;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Override
    public UUID save(AssetDto assetDto) {
        log.info("Save Asset: {}", assetDto);
        var entity = assetMapper.formDtoToEntity(assetDto);
        var saved = assetRepository.save(entity);
        log.info("Saved Asset: {}", saved);

        return saved.getId();
    }

    @Override
    public AssetDto findById(UUID id) {
        log.info("Search for Id: {}", id);
        var entity = assetRepository.findById(id).orElse(null);
        log.info("Find: {}", entity);

        return assetMapper.formEntityToDto(entity);
    }

    @Override
    public AssetDto findFirst() {
        var assetEntities = assetRepository.findAll();
        var assetEntity = StreamSupport.stream(assetEntities.spliterator(), false)
                .findFirst()
                .orElse(null);
        return assetMapper.formEntityToDto(assetEntity);
    }
}
