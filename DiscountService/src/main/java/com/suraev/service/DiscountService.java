package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.DiscountResponse;

import java.math.BigDecimal;

public interface DiscountService {
     DiscountResponse calculateDiscountAmount(DiscountRequest discountRequest);
}
