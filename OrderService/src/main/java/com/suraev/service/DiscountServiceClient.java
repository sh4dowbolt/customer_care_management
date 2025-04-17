package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.OrderDTO;
import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;

import java.math.BigDecimal;

public interface DiscountServiceClient {

    BigDecimal getDiscountValue(DiscountRequest discountRequest);
}
