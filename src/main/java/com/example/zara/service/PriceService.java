package com.example.zara.service;

import com.example.zara.domain.Price;
import com.example.zara.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService implements IPriceService {

    @Autowired
    PriceRepository priceRepository;

    @Override
    public Optional<Price> findPrice(LocalDateTime date, Long productId, Integer brandId) {
        List<Price> prices = priceRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, date, productId, brandId);
        return prices.stream()
                .max(Comparator.comparing(Price::getPriority));
    }

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }
}
