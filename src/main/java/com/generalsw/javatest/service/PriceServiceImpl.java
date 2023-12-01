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
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public List<PriceOutputDto> findPriceByParams(LocalDateTime applicationDate, Long productId, Long brandId) {
        List<Price> foundPrices = priceRepository.findByParams(applicationDate, productId, brandId);
        return foundPrices.stream().map(priceMapper::toPriceOutputDto).toList();
    }

    @Override
    public List<PriceOutputDto> fetchAll() {
        return priceRepository.findAll().stream().map(priceMapper::toPriceOutputDto).toList();
    }
}
