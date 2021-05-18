package pl.dicedev.kakebo.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dicedev.kakebo.annotations.LogDebug;
import pl.dicedev.kakebo.annotations.LogInfo;
import pl.dicedev.kakebo.enums.AssetCategory;
import pl.dicedev.kakebo.mappers.AssetMapper;
import pl.dicedev.kakebo.repositories.AssetRepository;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;
import pl.dicedev.kakebo.validators.AssetValidator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;
    private final UserLogInfoService userLogInfoService;
    private final AssetValidator assetValidator;

    @Override
    @LogInfo
    @LogDebug
    public UUID save(AssetDto assetDto) {
        assetValidator.valid(assetDto);

        var entity = assetMapper.fromDtoToEntity(assetDto, userLogInfoService.getLoggedUserEntity());
        var saved = assetRepository.save(entity);

        return saved.getId();
    }

    @Override
    @LogInfo
    @LogDebug
    public AssetDto findById(UUID id) {
        var entity = assetRepository.findById(id).orElse(null);
        return assetMapper.fromEntityToDto(entity);
    }

    @Override
    public List<AssetDto> findAll() {
        log.info("Find all assets");
        return assetMapper.fromEntityToDto(assetRepository.findAll());
    }

    @Override
    @LogInfo
    @LogDebug
    public List<AssetDto> getAssetByCategory(String category) {
        return assetRepository.getAssetEntitiesByAssetCategory(
                AssetCategory.valueOf(category.toUpperCase()))
                .stream()
                .map(assetMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAssetsByUser(UserEntity userEntity) {
        assetRepository.deleteAllByUser(userEntity);
    }
}
