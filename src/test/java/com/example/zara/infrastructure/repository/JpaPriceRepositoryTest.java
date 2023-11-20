package com.example.zara.infrastructure.repository;

import com.example.zara.domain.model.Price;
import com.example.zara.infrastructure.model.PriceEntity;
import com.example.zara.infrastructure.springdata.SpringDataJpaPriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class JpaPriceRepositoryTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @MockBean
    private SpringDataJpaPriceRepository springDataJpaPriceRepository;


    @Test
    void testGetPrice_At10AMOnDay14() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(createPriceEntity(1L, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1, 35455L, 0, new BigDecimal("35.50"), "EUR"));
        when(springDataJpaPriceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
                any(), any(), any(), any(), any()))
                .thenReturn(priceEntities);

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price result = jpaPriceRepository.getPrice(1L, 35455L, requestTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("35.50"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }


    @Test
    void testGetPrice_At4PMOnDay14() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(createPriceEntity(1L, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                2, 35455L, 1, new BigDecimal("25.45"), "EUR"));
        when(springDataJpaPriceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
                any(), any(), any(), any(), any()))
                .thenReturn(priceEntities);

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 16, 0);
        Price result = jpaPriceRepository.getPrice(1L, 35455L, requestTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("25.45"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }


    @Test
    void testGetPrice_At9PMOnDay14() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(createPriceEntity(1L, LocalDateTime.of(2020, 6, 14, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                4, 35455L, 1, new BigDecimal("38.95"), "EUR"));
        when(springDataJpaPriceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
                any(), any(), any(), any(), any()))
                .thenReturn(priceEntities);

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 21, 0);
        Price result = jpaPriceRepository.getPrice(1L, 35455L, requestTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("38.95"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }


    @Test
    void testGetPrice_At10AMOnDay15() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(createPriceEntity(1L, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                3, 35455L, 1, new BigDecimal("30.50"), "EUR"));
        when(springDataJpaPriceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
                any(), any(), any(), any(), any()))
                .thenReturn(priceEntities);

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 15, 10, 0);
        Price result = jpaPriceRepository.getPrice(1L, 35455L, requestTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("30.50"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }


    @Test
    void testGetPrice_At9PMOnDay16() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(createPriceEntity(1L, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                4, 35455L, 1, new BigDecimal("38.95"), "EUR"));
        when(springDataJpaPriceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
                any(), any(), any(), any(), any()))
                .thenReturn(priceEntities);

        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 16, 21, 0);
        Price result = jpaPriceRepository.getPrice(1L, 35455L, requestTime);

        assertEquals(1L, result.getBrandId());
        assertEquals(35455L, result.getProductId());
        assertEquals(new BigDecimal("38.95"), result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }


    private PriceEntity createPriceEntity(
            Long brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList,
            Long productId, Integer priority, BigDecimal price, String currency) {
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
