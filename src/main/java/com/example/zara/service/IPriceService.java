package com.example.zara.service;

import com.example.zara.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface IPriceService {

    List<Price> findPrice(LocalDateTime date, Long productId, Integer brandId);

    List<Price> findAll();
}
