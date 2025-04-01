package com.suraev.dto;

import com.suraev.entity.enums.ProductCategory;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

public record ProductDTO(Integer id, String name, BigDecimal price, ProductCategory category) {

}
