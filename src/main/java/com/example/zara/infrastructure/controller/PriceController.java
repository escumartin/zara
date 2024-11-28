package com.example.zara.infrastructure.controller;

import com.example.zara.application.impl.PriceServiceImpl;
import com.example.zara.infrastructure.dto.PriceDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Price API", tags = "Price Management")
public class PriceController {

    private final PriceServiceImpl priceService;

    @Autowired
    public PriceController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @ApiOperation(value = "Retrieve price information for a product", notes = "Fetches the applicable price for a product based on the provided date, product ID, and brand ID.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Price successfully retrieved."),
            @ApiResponse(code = 404, message = "No price found for the provided parameters."),
            @ApiResponse(code = 400, message = "Invalid input parameters."),
            @ApiResponse(code = 500, message = "Internal server error") })
    @GetMapping("/prices")
    public ResponseEntity<PriceDTO> getPrice(
            @ApiParam(value = "The date when the price is queried.", required = true) @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @ApiParam(value = "The ID of the product.", required = true) @RequestParam(name = "productId") Long productId,
            @ApiParam(value = "The ID of the brand.", required = true) @RequestParam(name = "brandId") Long brandId) {

        return ResponseEntity.ok(priceService.getPrice(brandId, productId, date));
    }
}
