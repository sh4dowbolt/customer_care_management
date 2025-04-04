package com.suraev.dto;

import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;

import java.math.BigDecimal;

public record DiscountRequest(UserType userType, ProductCategory productCategory, BigDecimal price) {
}
