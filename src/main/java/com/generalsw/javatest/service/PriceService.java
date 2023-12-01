package com.generalsw.javatest.service;

import com.generalsw.javatest.model.dto.PriceOutputDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
    List<PriceOutputDto> findPriceByParams(LocalDateTime applicationDate, Long productId, Long brandId);
    List<PriceOutputDto> fetchAll();
}
