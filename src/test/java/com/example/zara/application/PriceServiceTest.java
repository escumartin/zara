package com.example.zara.application;

import com.example.zara.application.impl.PriceServiceImpl;
import com.example.zara.domain.model.Price;
import com.example.zara.domain.repository.PriceDomainRepository;
import com.example.zara.infrastructure.dto.PriceDTO;
import com.example.zara.infrastructure.mapper.PriceMapper;
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
class PriceServiceTest {

    @InjectMocks
    private PriceServiceImpl priceApplicationService;

    @Mock
    private PriceDomainRepository priceDomainRepository;

    @Mock
    private PriceMapper priceMapper;

    @ParameterizedTest
    @CsvSource({ "2020-06-14T10:00, 35.50, EUR", "2020-06-14T16:00, 25.45, EUR", "2020-06-14T21:00, 38.95, EUR",
            "2020-06-15T10:00, 30.50, EUR", "2020-06-16T21:00, 35.50, EUR" })
    void testGetPrice(LocalDateTime dateTime, String expectedPrice, String expectedCurrency) {
        when(priceDomainRepository.getPrice(any(), any(), any())).thenReturn(
                createPrice(1L, 35455L, new BigDecimal(expectedPrice), expectedCurrency));
        when(priceMapper.toDTO(any())).thenReturn(createPriceDTO(1L, 35455L, new BigDecimal(expectedPrice), expectedCurrency));

        PriceDTO result = priceApplicationService.getPrice(1L, 35455L, dateTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal(expectedPrice), result.getPrice());
        assertEquals(expectedCurrency, result.getCurrency());
    }

    private Price createPrice(Long brandId, Long productId, BigDecimal price, String currency) {
        return new Price(null, brandId, LocalDateTime.now(), LocalDateTime.now(), 1, productId, 0, price, currency);
    }

    private PriceDTO createPriceDTO(Long brandId, Long productId, BigDecimal price, String currency) {
        return new PriceDTO(null, brandId, LocalDateTime.now(), LocalDateTime.now(), 1, productId, 0, price, currency);
    }
}
