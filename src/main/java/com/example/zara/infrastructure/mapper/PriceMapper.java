package com.example.zara.infrastructure.mapper;

import com.example.zara.domain.model.Price;
import com.example.zara.infrastructure.dto.PriceDTO;
import com.example.zara.infrastructure.model.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    Price toDomain(PriceDTO priceDTO);

    PriceDTO toDTO(Price price);

    PriceEntity toEntity(Price price);

    Price toDomain(PriceEntity priceEntity);
}
