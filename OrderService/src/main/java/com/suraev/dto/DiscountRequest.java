package com.suraev.dto;

import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class DiscountRequest {

    private UserType userType;

    private ProductCategory productCategory;

    private BigDecimal price;
}
