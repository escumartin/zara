package com.example.zara.service;

import com.example.zara.domain.Price;
import com.example.zara.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService implements IPriceService {

    @Autowired
    PriceRepository priceRepository;

    @Override
    public List<Price> findPrice(LocalDateTime date, Long productId, Integer brandId) {
        return priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, date, productId, brandId);
    }

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }
}
