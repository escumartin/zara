package com.example.zara.application;

import com.example.zara.infrastructure.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PriceService {
    PriceDTO getPrice(Long brandId, Long productId, LocalDateTime date);
}
