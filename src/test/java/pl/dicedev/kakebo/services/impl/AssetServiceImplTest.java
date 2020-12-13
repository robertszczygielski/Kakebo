package pl.dicedev.kakebo.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.dicedev.kakebo.mappers.AssetMapper;
import pl.dicedev.kakebo.mappers.AssetMapperImpl;
import pl.dicedev.kakebo.repositories.AssetRepository;
import pl.dicedev.kakebo.repositories.entities.AssetEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.security.UserDetailsRepository;
import pl.dicedev.kakebo.security.bto.UserBto;
import pl.dicedev.kakebo.services.AssetService;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AssetServiceImplTest {

    private AssetService assetService;

    @Mock
    private AssetRepository assetRepository;
    @Mock
    private UserDetailsRepository userDetailsRepository;

    @BeforeEach
    public void init() {
        AssetMapper assetMapper = new AssetMapperImpl();
        assetService = new AssetServiceImpl(assetRepository, userDetailsRepository, assetMapper);
    }

    @Test
    void shouldCallSave() {
        // given
        var userId = UUID.randomUUID();
        var userBto = UserBto.builder().id(userId).build();
        var dto = new AssetDto();
        var entity = new AssetEntity();
        var entityFromDto = new AssetEntity();
        var  userEntity = new UserEntity();

        dto.setAmount(BigDecimal.ONE);
        entityFromDto.setAmount(BigDecimal.ONE);
        entityFromDto.setUser(userEntity);
        userEntity.setId(userId);
        entity.setId(UUID.randomUUID());
        entity.setUser(userEntity);

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userBto);

        Mockito.when(assetRepository.save(entityFromDto)).thenReturn(entity);
        Mockito.when(userDetailsRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // when
        var result = assetService.save(dto);

        // then
        assertThat(result).isNotNull();

    }

    @Test
    void shouldReturnNullIfEntityIsNotFound() {
        // given
        var id = UUID.randomUUID();
        Mockito.when(assetRepository.findById(id)).thenReturn(Optional.empty());

        // when
        var result = assetService.findById(id);

        // then
        assertThat(result).isNull();
    }

    @Test
    void shouldReturnAssetIfEntityIsFound() {
        // given
        var id = UUID.randomUUID();
        var asset = new AssetEntity();
        asset.setId(id);
        asset.setAmount(BigDecimal.TEN);
        Mockito.when(assetRepository.findById(id)).thenReturn(Optional.of(asset));

        // when
        var result = assetService.findById(id);

        // then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getAmount()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void shouldReturnAllAssetsWhoWasMappedFromEntityToDto() {
        // given
        var numberOfAssets = 3;
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