package com.suraev.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

public record ProductDTO(Integer id, String name, BigDecimal price,String category) {

}
