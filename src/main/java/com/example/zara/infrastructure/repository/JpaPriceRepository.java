package com.example.zara.infrastructure.repository;


import com.example.zara.domain.excepiton.PriceNotFoundException;
import com.example.zara.domain.excepiton.RepositoryException;
import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceRepository;
import com.example.zara.infrastructure.mapper.PriceMapper;
import com.example.zara.infrastructure.model.PriceEntity;
import com.example.zara.infrastructure.springdata.SpringDataJpaPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class JpaPriceRepository implements PriceRepository {

    private final SpringDataJpaPriceRepository springDataJpaPriceRepository;


    @Autowired
    public JpaPriceRepository(SpringDataJpaPriceRepository springDataJpaPriceRepository) {
        this.springDataJpaPriceRepository = springDataJpaPriceRepository;
    }


    @Override
    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        try {
            List<PriceEntity> priceEntities = springDataJpaPriceRepository
                    .findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
                            brandId, productId, date, date, 0);

            if (!priceEntities.isEmpty()) {
                return PriceMapper.mapToDomain(priceEntities.get(0));
            }

            throw new PriceNotFoundException("Price not found for brandId, productId, and date.");

        } catch (Exception e) {
            throw new RepositoryException("Error while retrieving price.", e);
        }

    }
}
