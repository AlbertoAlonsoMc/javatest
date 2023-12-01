package com.generalsw.javatest.model.mapper;

import com.generalsw.javatest.model.dto.PriceOutputDto;
import com.generalsw.javatest.model.Price;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceMapperImpl implements PriceMapper{
    private final ModelMapper modelMapper;

    @Override
    public PriceOutputDto toPriceOutputDto(Price price) {
        return modelMapper.map(price, PriceOutputDto.class);
    }
}
