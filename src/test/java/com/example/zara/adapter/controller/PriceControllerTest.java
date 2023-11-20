package com.example.zara.adapter.controller;

import com.example.zara.application.PriceApplicationService;
import com.example.zara.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PriceControllerTest {

    @Mock
    private PriceApplicationService priceApplicationService;

    @InjectMocks
    private PriceController priceController;

    @Test
    void testRequestAt10AMOnDay14() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long brandId = 1L;
        Long productId = 35455L;

        Price expectedPrice = new Price(1L, date.minusHours(1), date.plusHours(1), 1, productId, 0, new BigDecimal("35.50"), "EUR");

        when(priceApplicationService.getPrice(brandId, productId, date)).thenReturn(expectedPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        // Assert
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, date);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    void testRequestAt4PMOnDay14() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long brandId = 1L;
        Long productId = 35455L;

        Price expectedPrice = new Price(1L, date.minusHours(1), date.plusHours(1), 2, productId, 1, new BigDecimal("25.45"), "EUR");

        when(priceApplicationService.getPrice(brandId, productId, date)).thenReturn(expectedPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        // Assert
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, date);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    void testRequestAt9PMOnDay14() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
        Long brandId = 1L;
        Long productId = 35455L;

        Price expectedPrice = new Price(1L, date.minusHours(1), date.plusHours(1), 1, productId, 0, new BigDecimal("35.50"), "EUR");

        when(priceApplicationService.getPrice(brandId, productId, date)).thenReturn(expectedPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        // Assert
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, date);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    void testRequestAt10AMOnDay15() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        Long brandId = 1L;
        Long productId = 35455L;

        Price expectedPrice = new Price(1L, date.minusHours(1), date.plusHours(1), 3, productId, 1, new BigDecimal("30.50"), "EUR");

        when(priceApplicationService.getPrice(brandId, productId, date)).thenReturn(expectedPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        // Assert
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, date);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPrice, response.getBody());
    }

    @Test
    void testRequestAt9PMOnDay16() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
        Long brandId = 1L;
        Long productId = 35455L;

        // Assuming no price is available for the given parameters
        when(priceApplicationService.getPrice(brandId, productId, date)).thenReturn(null);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        // Assert
        verify(priceApplicationService, times(1)).getPrice(brandId, productId, date);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
