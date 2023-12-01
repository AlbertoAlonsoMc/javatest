package com.generalsw.javatest.service;

import com.generalsw.javatest.model.mapper.PriceMapper;
import com.generalsw.javatest.model.dto.PriceOutputDto;
import com.generalsw.javatest.repository.PriceRepository;
import org.junit.jupiter.api.BeforeAll;
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

    private static List<LocalDateTime> testDates;
    private static Long productId;
    private static Long brandId;

    @BeforeAll
    static void setUp() {
        testDates = List.of(
                LocalDateTime.of(2020, 6, 14, 10, 0),
                LocalDateTime.of(2020, 6, 14, 16, 0),
                LocalDateTime.of(2020, 6, 14, 21, 0),
                LocalDateTime.of(2020, 6, 15, 10, 0),
                LocalDateTime.of(2020, 6, 16, 21, 0)
        );

        productId = 35455L;
        brandId = 1L;
    }

    @Test
    void findByParams_returnsAccuratePrices() {
        List<PriceOutputDto> pricesFoundDate1 = priceRepository.findByParams(testDates.get(0), productId, brandId).stream().map(priceMapper::toPriceOutputDto).toList();
        List<PriceOutputDto> pricesFoundDate2 = priceRepository.findByParams(testDates.get(1), productId, brandId).stream().map(priceMapper::toPriceOutputDto).toList();
        List<PriceOutputDto> pricesFoundDate3 = priceRepository.findByParams(testDates.get(2), productId, brandId).stream().map(priceMapper::toPriceOutputDto).toList();
        List<PriceOutputDto> pricesFoundDate4 = priceRepository.findByParams(testDates.get(3), productId, brandId).stream().map(priceMapper::toPriceOutputDto).toList();
        List<PriceOutputDto> pricesFoundDate5 = priceRepository.findByParams(testDates.get(4), productId, brandId).stream().map(priceMapper::toPriceOutputDto).toList();

        //Assertion of AMOUNT  of received results
        assertEquals(1, pricesFoundDate1.size()); //Data example 1
        assertEquals(2, pricesFoundDate2.size()); //Data example 2
        assertEquals(1, pricesFoundDate3.size()); //Data example 3
        assertEquals(2, pricesFoundDate4.size()); //Data example 4
        assertEquals(2, pricesFoundDate5.size()); //Data example 5


        //Assertion of DATE between range of dates (private method)
        assertDateRange(testDates.get(0), pricesFoundDate1.get(0).getStartDate(), pricesFoundDate1.get(0).getEndDate()); //Data example 1

        assertDateRange(testDates.get(1), pricesFoundDate2.get(0).getStartDate(), pricesFoundDate2.get(0).getEndDate()); //Data example 2
        assertDateRange(testDates.get(1), pricesFoundDate2.get(1).getStartDate(), pricesFoundDate2.get(1).getEndDate()); //Data example 2

        assertDateRange(testDates.get(2), pricesFoundDate3.get(0).getStartDate(), pricesFoundDate3.get(0).getEndDate()); //Data example 3

        assertDateRange(testDates.get(3), pricesFoundDate4.get(0).getStartDate(), pricesFoundDate4.get(0).getEndDate()); //Data example 4
        assertDateRange(testDates.get(3), pricesFoundDate4.get(1).getStartDate(), pricesFoundDate4.get(1).getEndDate()); //Data example 4

        assertDateRange(testDates.get(4), pricesFoundDate5.get(0).getStartDate(), pricesFoundDate5.get(0).getEndDate()); //Data example 5
        assertDateRange(testDates.get(4), pricesFoundDate5.get(1).getStartDate(), pricesFoundDate5.get(1).getEndDate()); //Data example 5


        //Assertion of PRODUCT ID
        assertEquals(35455, pricesFoundDate1.get(0).getProductId()); //Data example 1

        assertEquals(35455, pricesFoundDate2.get(0).getProductId()); //Data example 2
        assertEquals(35455, pricesFoundDate2.get(1).getProductId()); //Data example 2

        assertEquals(35455, pricesFoundDate3.get(0).getProductId()); //Data example 3

        assertEquals(35455, pricesFoundDate4.get(0).getProductId()); //Data example 4
        assertEquals(35455, pricesFoundDate4.get(1).getProductId()); //Data example 4

        assertEquals(35455, pricesFoundDate5.get(0).getProductId()); //Data example 5
        assertEquals(35455, pricesFoundDate5.get(1).getProductId()); //Data example 5


        //Assertion of product BRAND ID
        assertEquals(1, pricesFoundDate1.get(0).getBrandId()); //Data example 1

        assertEquals(1, pricesFoundDate2.get(0).getBrandId()); //Data example 2
        assertEquals(1, pricesFoundDate2.get(1).getBrandId()); //Data example 2

        assertEquals(1, pricesFoundDate3.get(0).getBrandId()); //Data example 3

        assertEquals(1, pricesFoundDate4.get(0).getBrandId()); //Data example 4
        assertEquals(1, pricesFoundDate4.get(1).getBrandId()); //Data example 4

        assertEquals(1, pricesFoundDate5.get(0).getBrandId()); //Data example 5
        assertEquals(1, pricesFoundDate5.get(1).getBrandId()); //Data example 5
    }

    private void assertDateRange(LocalDateTime date, LocalDateTime initialDate, LocalDateTime endDate) {
        assertTrue(date.isAfter(initialDate) && date.isBefore(endDate));
    }
}
