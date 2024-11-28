package com.example.zara.application.impl;

import com.example.zara.application.PriceService;
import com.example.zara.domain.repository.PriceDomainRepository;
import com.example.zara.infrastructure.dto.PriceDTO;
import com.example.zara.infrastructure.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceDomainRepository priceDomainRepository;
    private final PriceMapper priceMapper;

    public PriceDTO getPrice(Long brandId, Long productId, LocalDateTime date) {
        return priceMapper.toDTO(priceDomainRepository.getPrice(brandId, productId, date));
    }
}
