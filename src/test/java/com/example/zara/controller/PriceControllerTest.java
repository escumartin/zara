package com.example.zara.controller;

import com.example.zara.domain.Price;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

    @Autowired
    PriceController priceController;


    @Test
    void getPriceTest_1() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Long productId = 35455L;
        Integer brandId = 1;

        asserts(date, productId, brandId);
    }


    @Test
    void getPriceTest_2() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        Long productId = 35455L;
        Integer brandId = 1;

        asserts(date, productId, brandId);
    }


    @Test
    void getPriceTest_3() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        Long productId = 35455L;
        Integer brandId = 1;

        asserts(date, productId, brandId);
    }


    @Test
    void getPriceTest_4() {
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        Long productId = 35455L;
        Integer brandId = 1;

        asserts(date, productId, brandId);
    }


    @Test
    void getPriceTest_5() {
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        Long productId = 35455L;
        Integer brandId = 1;

        asserts(date, productId, brandId);
    }


    private void asserts(LocalDateTime date, Long productId, Integer brandId) {
        ResponseEntity<Optional<Price>> response = priceController.getPrice(date, productId, brandId);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<Price> price = response.getBody();
        if (price != null && price.isPresent()) {
            Assert.assertEquals(brandId, price.get().getBrandId());
            Assert.assertEquals(productId, price.get().getProductId());
            Assert.assertTrue(price.get().getStartDate().isBefore(date) && price.get().getEndDate().isAfter(date));
        }
    }
}