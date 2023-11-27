package com.example.zara.infrastructure.repository.controller;

import com.example.zara.application.PriceApplicationService;
import com.example.zara.domain.model.Price;
import com.example.zara.infrastructure.controller.PriceController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class PriceControllerTest {

    @Mock
    private PriceApplicationService priceApplicationService;

    @InjectMocks
    private PriceController priceController;

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;


    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00, 1, 35.50",
            "2020-06-14T16:00, 2, 25.45",
            "2020-06-14T21:00, 1, 35.50",
            "2020-06-15T10:00, 3, 30.50",
            "2020-06-16T21:00, 3, 38.95"
    })
    void testGetPrice(LocalDateTime date, int expectedPriority, String expectedPrice) {
        // Arrange
        Price expectedPriceObject = createExpectedPrice(date, expectedPriority, expectedPrice);
        when(priceApplicationService.getPrice(BRAND_ID, PRODUCT_ID, date)).thenReturn(expectedPriceObject);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, PRODUCT_ID, BRAND_ID);

        // Assert
        verify(priceApplicationService, times(1)).getPrice(BRAND_ID, PRODUCT_ID, date);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPriceObject, response.getBody());
    }

    private Price createExpectedPrice(LocalDateTime date, int priority, String priceValue) {
        return new Price(BRAND_ID, date.minusHours(1), date.plusHours(1), priority, PRODUCT_ID,
                1, new BigDecimal(priceValue), "EUR");
    }
}
