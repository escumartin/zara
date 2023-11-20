package com.example.zara.application;

import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceApplicationServiceTest {

    @InjectMocks
    private PriceApplicationService priceApplicationService;

    @Mock
    private PriceRepository priceRepository;

    @Test
    void testGetPrice_At10AMOnDay14() {
        when(priceRepository.getPrice(any(), any(), any()))
                .thenReturn(createPrice(1L, 35455L, new BigDecimal("35.50"), "EUR"));

        Price result = priceApplicationService.getPrice(1L, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0));

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("35.50"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }

    @Test
    void testGetPrice_At4PMOnDay14() {
        when(priceRepository.getPrice(any(), any(), any()))
                .thenReturn(createPrice(1L, 35455L, new BigDecimal("25.45"), "EUR"));

        Price result = priceApplicationService.getPrice(1L, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0));

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("25.45"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }

    @Test
    void testGetPrice_At9PMOnDay14() {
        when(priceRepository.getPrice(any(), any(), any()))
                .thenReturn(createPrice(1L, 35455L, new BigDecimal("38.95"), "EUR"));

        Price result = priceApplicationService.getPrice(1L, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0));

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("38.95"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }

    @Test
    void testGetPrice_At10AMOnDay15() {
        when(priceRepository.getPrice(any(), any(), any()))
                .thenReturn(createPrice(1L, 35455L, new BigDecimal("30.50"), "EUR"));

        Price result = priceApplicationService.getPrice(1L, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0));

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("30.50"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }

    @Test
    void testGetPrice_At9PMOnDay16() {
        when(priceRepository.getPrice(any(), any(), any()))
                .thenReturn(createPrice(1L, 35455L, new BigDecimal("35.50"), "EUR"));

        Price result = priceApplicationService.getPrice(1L, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0));

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("35.50"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }

    private Price createPrice(Long brandId, Long productId, BigDecimal price, String currency) {
        return new Price(brandId, LocalDateTime.now(), LocalDateTime.now(), 1, productId, 0, price, currency);
    }
}