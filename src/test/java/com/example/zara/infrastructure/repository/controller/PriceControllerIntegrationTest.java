package com.example.zara.infrastructure.repository.controller;

import com.example.zara.infrastructure.repository.jpa.PriceJpaRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testGetPrice(String date, int productId, int brandId, double price, String currency, int priceList,
            String startDate, String endDate, int priority) throws Exception {
        mockMvc.perform(get("/api/v1/prices").param("date", date).param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))).andExpect(jsonPath("$.productId", is(productId)))
                .andExpect(jsonPath("$.brandId", is(brandId))).andExpect(jsonPath("$.price", is(price)))
                .andExpect(jsonPath("$.currency", is(currency))).andExpect(jsonPath("$.priceList", is(priceList)))
                .andExpect(jsonPath("$.startDate", is(startDate))).andExpect(jsonPath("$.endDate", is(endDate)))
                .andExpect(jsonPath("$.priority", is(priority)));
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("2020-06-14T10:00:00", 35455, 1, 35.50, "EUR", 1, "2020-06-14T00:00:00",
                        "2020-12-31T23:59:59", 0),
                Arguments.of("2020-06-14T16:00:00", 35455, 1, 25.45, "EUR", 2, "2020-06-14T15:00:00",
                        "2020-06-14T18:30:00", 1),
                Arguments.of("2020-06-14T21:00:00", 35455, 1, 35.50, "EUR", 1, "2020-06-14T00:00:00",
                        "2020-12-31T23:59:59", 0),
                Arguments.of("2020-06-15T10:00:00", 35455, 1, 30.50, "EUR", 3, "2020-06-15T00:00:00",
                        "2020-06-15T11:00:00", 1),
                Arguments.of("2020-06-16T21:00:00", 35455, 1, 38.95, "EUR", 4, "2020-06-15T16:00:00",
                        "2020-12-31T23:59:59", 1));
    }

}
