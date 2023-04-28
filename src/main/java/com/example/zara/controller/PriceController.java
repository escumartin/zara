package com.example.zara.controller;

import com.example.zara.domain.Price;
import com.example.zara.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class PriceController {

    @Autowired
    IPriceService priceService;


    @GetMapping("/price")
    public ResponseEntity<Optional<Price>> getPrice(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "brandId") Integer brandId) {

        Optional<Price> prices = priceService.findPrice(date, productId, brandId);

        if (prices.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prices);
        }
    }


    @GetMapping("/prices")
    public ResponseEntity<List<Price>> listAll() {

        List<Price> prices = priceService.findAll();

        if (prices.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prices);
        }
    }
}
