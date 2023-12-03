package com.generalsw.javatest.controller;

import com.generalsw.javatest.model.dto.PriceOutputDto;
import com.generalsw.javatest.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor //Constructor tag (for dependency injection)
public class PriceController {

    //Declared as final because of the dependency injection by @RequiredArgsConstructor
    private final PriceService priceService;

    @GetMapping("/findPrice")
    public ResponseEntity<List<PriceOutputDto>> findPriceByParams(
            @RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        return new ResponseEntity<>(priceService.findPriceByParams(applicationDate, productId, brandId), HttpStatus.OK);
    }
}
