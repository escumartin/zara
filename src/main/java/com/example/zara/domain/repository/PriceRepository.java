package com.example.zara.domain.repository;

import com.example.zara.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price getPrice(Long brandId, Long productId, LocalDateTime date);
}

