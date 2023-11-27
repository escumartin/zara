package com.example.zara.application;

import com.example.zara.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceApplicationService {
    Price getPrice(Long brandId, Long productId, LocalDateTime date);
}
