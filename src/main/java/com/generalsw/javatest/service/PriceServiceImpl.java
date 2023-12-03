package com.generalsw.javatest.service;

import com.generalsw.javatest.model.Price;
import com.generalsw.javatest.model.mapper.PriceMapper;
import com.generalsw.javatest.model.dto.PriceOutputDto;
import com.generalsw.javatest.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor //Constructor tag (for dependency injection)
public class PriceServiceImpl implements PriceService {

    //Declared as final because of the dependency injection by @RequiredArgsConstructor
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public List<PriceOutputDto> findPriceByParams(LocalDateTime applicationDate, Long productId, Long brandId) {
        List<Price> foundPrices = priceRepository.findByParams(applicationDate, productId, brandId);
        return foundPrices.stream().map(priceMapper::toPriceOutputDto).toList();
    }
}
