package com.example.zara.application;

import com.example.zara.application.impl.PriceApplicationServiceImpl;
import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    private PriceApplicationServiceImpl priceApplicationService;

    @Mock
    private PriceRepository priceRepository;


    @ParameterizedTest
    @CsvSource({
            "2020-06-14T10:00, 35.50, EUR",
            "2020-06-14T16:00, 25.45, EUR",
            "2020-06-14T21:00, 38.95, EUR",
            "2020-06-15T10:00, 30.50, EUR",
            "2020-06-16T21:00, 35.50, EUR"
    })
    void testGetPrice(LocalDateTime dateTime, String expectedPrice, String expectedCurrency) {
        when(priceRepository.getPrice(any(), any(), any()))
                .thenReturn(createPrice(1L, 35455L, new BigDecimal(expectedPrice), expectedCurrency));

        Price result = priceApplicationService.getPrice(1L, 35455L, dateTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal(expectedPrice), result.getPrice());
        assertEquals(expectedCurrency, result.getCurrency());
    }

    private Price createPrice(Long brandId, Long productId, BigDecimal price, String currency) {
        return new Price(brandId, LocalDateTime.now(), LocalDateTime.now(), 1, productId, 0, price, currency);
    }
}