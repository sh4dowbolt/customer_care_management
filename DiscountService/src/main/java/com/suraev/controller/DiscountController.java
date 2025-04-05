package com.suraev.controller;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.DiscountResponse;
import com.suraev.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

    private DiscountService discountService;

    @PostMapping("/getDiscount")
    public ResponseEntity<DiscountResponse> getDiscount(@RequestBody DiscountRequest discountRequest) {
        return new ResponseEntity<>(discountService.calculateDiscountAmount(discountRequest), HttpStatus.OK);
    }
}
