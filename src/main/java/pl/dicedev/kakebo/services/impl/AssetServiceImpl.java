package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.mappers.AssetMapper;
import pl.dicedev.kakebo.repositories.AssetRepository;
import pl.dicedev.kakebo.repositories.entities.AssetEntity;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;
import pl.dicedev.kakebo.validators.AssetValidator;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;
    private final UserLogInfoService userLogInfoService;
    private final AssetValidator assetValidator;

    @Override
    public UUID save(AssetDto assetDto) {
        log.info("Save Asset: {}", assetDto);

        assetValidator.valid(assetDto);

        var entity = assetMapper.fromDtoToEntity(assetDto, userLogInfoService.getLoggedUserEntity());
        var saved = assetRepository.save(entity);
        log.info("Saved Asset: {}", saved);

        return saved.getId();
    }

    @Override
    public AssetDto findById(UUID id) {
        log.info("Search for Id: {}", id);
        var entity = assetRepository.findById(id).orElse(null);
        log.info("Find: {}", entity);

        return assetMapper.fromEntityToDto(entity);
    }

    @Override
    public List<AssetDto> findAll() {
        log.info("Find all assets");
        return assetMapper.fromEntityToDto(assetRepository.findAll());
    }
}
