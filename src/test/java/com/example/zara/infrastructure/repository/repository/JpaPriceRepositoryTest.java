package com.example.zara.infrastructure.repository.repository;

import com.example.zara.infrastructure.model.PriceEntity;
import com.example.zara.infrastructure.springdata.SpringDataJpaPriceRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
class JpaPriceRepositoryTest {

    @Autowired
    private SpringDataJpaPriceRepository jpaPriceRepository;

    @ParameterizedTest
    @CsvSource({ "2020-06-14T10:00, 35.50, EUR, 1, 35455, 0, 2020-06-14T00:00, 2020-12-31T23:59:59",
            "2020-06-14T16:00, 25.45, EUR, 2, 35455, 1, 2020-06-14T15:00, 2020-06-14T18:30:00",
            "2020-06-14T21:00, 35.50, EUR, 4, 35455, 0, 2020-06-14T00:00, 2020-12-31T23:59:59",
            "2020-06-15T10:00, 30.50, EUR, 3, 35455, 1, 2020-06-15T00:00, 2020-06-15T11:00:00",
            "2020-06-16T21:00, 38.95, EUR, 4, 35455, 1, 2020-06-15T16:00, 2020-12-31T23:59:59" })
    void testGetPrice(String requestTimeStr, String expectedPrice, String expectedCurrency, Integer priceList,
            Long productId, Integer priority, String startDateStr, String endDateStr) {

        LocalDateTime requestTime = LocalDateTime.parse(requestTimeStr);
        LocalDateTime startDate = LocalDateTime.parse(startDateStr);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr);

        PriceEntity expectedPriceEntity = createPriceEntity(1L, startDate, endDate, priceList, productId, priority,
                new BigDecimal(expectedPrice), expectedCurrency);

        List<PriceEntity> priceEntities = jpaPriceRepository.findApplicablePrices(expectedPriceEntity.getBrandId(),
                expectedPriceEntity.getProductId(), requestTime);

        assertFalse(priceEntities.isEmpty(), "Expected at least one price entity for request time " + requestTime);

        PriceEntity actualPriceEntity = priceEntities.stream().max(Comparator.comparingInt(PriceEntity::getPriority))
                .orElseThrow(() -> new AssertionError("No entity found with the highest priority"));

        assertEquals(expectedPriceEntity.getPrice(), actualPriceEntity.getPrice(), "Price should match.");
        assertEquals(expectedPriceEntity.getCurrency(), actualPriceEntity.getCurrency(), "Currency should match.");
        assertEquals(expectedPriceEntity.getPriority(), actualPriceEntity.getPriority(), "Priority should match.");

    }

    private PriceEntity createPriceEntity(Long brandId, LocalDateTime startDate, LocalDateTime endDate,
            Integer priceList, Long productId, Integer priority, BigDecimal price, String currency) {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setBrandId(brandId);
        priceEntity.setStartDate(startDate);
        priceEntity.setEndDate(endDate);
        priceEntity.setPriceList(priceList);
        priceEntity.setProductId(productId);
        priceEntity.setPriority(priority);
        priceEntity.setPrice(price);
        priceEntity.setCurrency(currency);
        return priceEntity;
    }
}

