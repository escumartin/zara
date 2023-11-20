package com.example.zara.application;

import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceApplicationService {

    private final PriceRepository priceRepository;


    @Autowired
    public PriceApplicationService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceRepository.getPrice(brandId, productId, date);
    }
}
