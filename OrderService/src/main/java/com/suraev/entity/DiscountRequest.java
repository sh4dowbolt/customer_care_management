package com.suraev.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DiscountRequest {
    private String userType;
    private String productType;
    private BigDecimal price;
}
