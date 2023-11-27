package com.example.zara.infrastructure.mapper;

import com.example.zara.domain.model.Price;
import com.example.zara.infrastructure.model.PriceEntity;
import org.modelmapper.ModelMapper;

public class PriceMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Price mapToDomain(PriceEntity priceEntity) {
        return (priceEntity != null) ? modelMapper.map(priceEntity, Price.class) : null;
    }
}
