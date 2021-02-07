package pl.dicedev.kakebo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.dicedev.kakebo.repositories.entities.AssetEntity;
import pl.dicedev.kakebo.repositories.entities.UserEntity;
import pl.dicedev.kakebo.services.dtos.AssetDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssetMapper {

    AssetDto fromEntityToDto(AssetEntity entity);

    @Mapping(source = "userEntity", target = "user")
    @Mapping(source = "dto.amount", target = "amount")
    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.incomeDate", target = "incomeDate", defaultExpression ="java(java.time.Instant.now())")
    @Mapping(source = "dto.assetCategory", target = "assetCategory", defaultExpression = "java(pl.dicedev.kakebo.enums.AssetCategory.OTHER)")
    AssetEntity fromDtoToEntity(AssetDto dto, UserEntity userEntity);

    List<AssetDto> fromEntityToDto(Iterable<AssetEntity> entities);

}
