package com.generalsw.javatest.service;

import com.generalsw.javatest.model.mapper.PriceMapper;
import com.generalsw.javatest.model.dto.PriceOutputDto;
import com.generalsw.javatest.repository.PriceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan("com.generalsw.javatest")
public class PriceServiceTest {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private PriceMapper priceMapper;

    private static List<LocalDateTime> givenDates;
    private static Long givenProductId;
    private static Long givenBrandId;
    private static List<List<PriceOutputDto>> listOfFoundPrices;

    @BeforeAll
    static void setUpBeforeAll() {
        givenDates = List.of(
                LocalDateTime.of(2020, 6, 14, 10, 0),
                LocalDateTime.of(2020, 6, 14, 16, 0),
                LocalDateTime.of(2020, 6, 14, 21, 0),
                LocalDateTime.of(2020, 6, 15, 10, 0),
                LocalDateTime.of(2020, 6, 16, 21, 0)
        );

        givenProductId = 35455L;
        givenBrandId = 1L;
    }

    @BeforeEach
    void setUpBeforeEach() {
        listOfFoundPrices = List.of(
                priceRepository.findByParams(givenDates.get(0), givenProductId, givenBrandId).stream().map(priceMapper::toPriceOutputDto).toList(),
                priceRepository.findByParams(givenDates.get(1), givenProductId, givenBrandId).stream().map(priceMapper::toPriceOutputDto).toList(),
                priceRepository.findByParams(givenDates.get(2), givenProductId, givenBrandId).stream().map(priceMapper::toPriceOutputDto).toList(),
                priceRepository.findByParams(givenDates.get(3), givenProductId, givenBrandId).stream().map(priceMapper::toPriceOutputDto).toList(),
                priceRepository.findByParams(givenDates.get(4), givenProductId, givenBrandId).stream().map(priceMapper::toPriceOutputDto).toList()
        );
    }

    @Test
    void assertionOfAmountReceivedResults() {
        assertAmountReceivedResults();
    }

    @Test
    void assertionOfDatesBetweenRanges() {
        assertDateRanges();
    }

    @Test
    void assertionOfProductIds() {
        assertProductIds(35455L);
    }

    @Test
    void assertionOfBrandIds() {
        assertBrandIds(1L);
    }


    /*--------------------------DINAMIC ITERATION METHODS OVER ALL RESULTS--------------------------*/
    private void assertAmountReceivedResults() {
        for (int i = 0; i < listOfFoundPrices.size(); i++) {
            switch (i) {
                case 0, 2:
                    assertEquals(1, listOfFoundPrices.get(i).size());
                    break;
                case 1, 3, 4:
                    assertEquals(2, listOfFoundPrices.get(i).size());
                    break;
                default:
            }
        }
    }

    private void assertDateRanges() {
        for (int i = 0; i < listOfFoundPrices.size(); i++) {
            for (int j = 0; i < listOfFoundPrices.get(i).size(); i++) {
                assertTrue(givenDates.get(i).
                        isAfter(listOfFoundPrices.get(i).get(j).getStartDate()) && givenDates.get(i).
                        isBefore(listOfFoundPrices.get(i).get(j).getEndDate()));
            }
        }
    }

    private void assertProductIds(Long productId) {
        for (List<PriceOutputDto> pricesFound : listOfFoundPrices
        ) {
            for (PriceOutputDto priceOutputDto : pricesFound) {
                assertEquals(productId, priceOutputDto.getProductId());
            }
        }
    }

    private void assertBrandIds(Long brandId) {
        for (List<PriceOutputDto> pricesFound : listOfFoundPrices
        ) {
            for (PriceOutputDto priceOutputDto : pricesFound) {
                assertEquals(brandId, priceOutputDto.getBrandId());
            }
        }
    }
}
