package com.example.zara.adapter.controller;

import com.example.zara.application.PriceApplicationService;
import com.example.zara.domain.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceApplicationService priceApplicationService;


    @Autowired
    public PriceController(PriceApplicationService priceApplicationService) {
        this.priceApplicationService = priceApplicationService;
    }


    @GetMapping("/getPrice")
    public ResponseEntity<Price> getPrice(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "brandId") Long brandId) {

        Price price = priceApplicationService.getPrice(brandId, productId, date);

        if (price != null) {
            return new ResponseEntity<>(price, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
