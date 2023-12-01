package com.generalsw.javatest.model.mapper;

import com.generalsw.javatest.model.dto.PriceOutputDto;
import com.generalsw.javatest.model.Price;

public interface PriceMapper {
    PriceOutputDto toPriceOutputDto(Price price);
}
