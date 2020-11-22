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
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AssetServiceImplTest {

    private AssetService assetService;
    private AssetMapper assetMapper;

    @Mock
    private AssetRepository assetRepository;

    @BeforeEach
    public void init() {
        AssetEntity entity = new AssetEntity();
        entity.setId(UUID.randomUUID());
        AssetEntity entityFromDto = new AssetEntity();
        entityFromDto.setAmount(BigDecimal.ONE);

        Mockito.when(assetRepository.save(entityFromDto)).thenReturn(entity);

        assetMapper = new AssetMapperImpl();
        assetService = new AssetServiceImpl(assetRepository, assetMapper);
    }

    @Test
    void shouldCallSave() {
        // given
        AssetDto dto = new AssetDto();
        dto.setAmount(BigDecimal.ONE);

        // when
        var result = assetService.save(dto);

        // then
        assertThat(result).isNotNull();

    }
}