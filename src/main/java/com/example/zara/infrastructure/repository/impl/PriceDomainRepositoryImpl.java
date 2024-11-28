package com.example.zara.infrastructure.repository.impl;

import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceDomainRepository;
import com.example.zara.infrastructure.exception.ResourceNotFoundException;
import com.example.zara.infrastructure.mapper.PriceMapper;
import com.example.zara.infrastructure.model.PriceEntity;
import com.example.zara.infrastructure.repository.jpa.PriceJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Repository
public class PriceDomainRepositoryImpl implements PriceDomainRepository {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceMapper priceMapper;

    @Override
    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        List<PriceEntity> priceEntities = priceJpaRepository.findApplicablePrices(brandId, productId, date);

        if (priceEntities.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Price not found for brandId: " + brandId + ", productId: " + productId + ", and date: " + date);
        }

        return priceMapper.toDomain(priceEntities.getFirst());
    }
}
