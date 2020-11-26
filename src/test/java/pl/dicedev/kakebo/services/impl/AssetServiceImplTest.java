package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dicedev.kakebo.mappers.AssetMapper;
import pl.dicedev.kakebo.mappers.AssetMapperImpl;
import pl.dicedev.kakebo.repositories.AssetRepository;
import pl.dicedev.kakebo.repositories.entities.AssetEntity;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AssetServiceImplTest {

    private AssetService assetService;
    private AssetMapper assetMapper;

    @Mock
    private AssetRepository assetRepository;

    @BeforeEach
    public void init() {
        assetMapper = new AssetMapperImpl();
        assetService = new AssetServiceImpl(assetRepository, assetMapper);
    }

    @Test
    void shouldCallSave() {
        // given
        AssetDto dto = new AssetDto();
        dto.setAmount(BigDecimal.ONE);
        AssetEntity entity = new AssetEntity();
        entity.setId(UUID.randomUUID());
        AssetEntity entityFromDto = new AssetEntity();
        entityFromDto.setAmount(BigDecimal.ONE);

        Mockito.when(assetRepository.save(entityFromDto)).thenReturn(entity);


        // when
        var result = assetService.save(dto);

        // then
        assertThat(result).isNotNull();

    }

    @Test
    void shouldReturnAllAssetsWhoWasMappedFromEntityToDto() {
        // given
        int numberOfAssets = 3;
        List<AssetEntity> assetEntities = prepareAssetEntities(numberOfAssets);
        Mockito.when(assetRepository.findAll()).thenReturn(assetEntities);

        // when
        var result = assetService.findAll();

        // then
        assertThat(result.size()).isEqualTo(numberOfAssets);


    }

    private List<AssetEntity> prepareAssetEntities(int numberOfAssets) {
        return IntStream.range(0, numberOfAssets)
                .mapToObj(it -> {
                    var assetEntity = new AssetEntity();
                    assetEntity.setAmount(new BigDecimal(it));
                    assetEntity.setId(UUID.randomUUID());
                    return assetEntity;
                })
                .collect(Collectors.toList());
    }
}