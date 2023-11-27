package com.example.zara.application.impl;

import com.example.zara.application.PriceApplicationService;
import com.example.zara.domain.excepiton.PriceApplicationException;
import com.example.zara.domain.excepiton.PriceNotFoundException;
import com.example.zara.domain.excepiton.RepositoryException;
import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceApplicationServiceImpl implements PriceApplicationService {

    private final PriceRepository priceRepository;


    @Autowired
    public PriceApplicationServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        try {
            return priceRepository.getPrice(brandId, productId, date);
        } catch (PriceNotFoundException e) {
            throw new PriceApplicationException("Error while getting price", e);
        } catch (RepositoryException e) {
            throw new PriceApplicationException("Error in repository while getting price", e);
        }
    }
}
