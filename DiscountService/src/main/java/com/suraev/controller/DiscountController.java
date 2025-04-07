package com.suraev.controller;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.DiscountResponse;
import com.suraev.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountServiceImpl;

    @PostMapping(value = "/getDiscount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> getDiscount(@RequestBody DiscountRequest discountRequest) {

        DiscountResponse discountResponse = discountServiceImpl.calculateDiscountAmount(discountRequest);
        BigDecimal returnDiscount = discountResponse.discountAmount();
        return new ResponseEntity<>(returnDiscount, HttpStatus.OK);
    }
}
