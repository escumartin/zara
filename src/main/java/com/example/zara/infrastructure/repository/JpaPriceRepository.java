package com.example.zara.infrastructure.repository;

import com.example.zara.domain.excepiton.PriceNotFoundException;
import com.example.zara.domain.excepiton.RepositoryException;
import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceRepository;
import com.example.zara.infrastructure.mapper.PriceMapper;
import com.example.zara.infrastructure.model.PriceEntity;
import com.example.zara.infrastructure.springdata.SpringDataJpaPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Repository
public class JpaPriceRepository implements PriceRepository {

    private final SpringDataJpaPriceRepository springDataJpaPriceRepository;

    @Override
    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        try {
            List<PriceEntity> priceEntities = springDataJpaPriceRepository.findApplicablePrices(brandId, productId,
                    date);

            if (!priceEntities.isEmpty()) {
                return PriceMapper.mapToDomain(priceEntities.getFirst());
            }

            throw new PriceNotFoundException("Price not found for brandId, productId, and date.");

        } catch (Exception e) {
            throw new RepositoryException("Error while retrieving price.", e);
        }

    }
}
