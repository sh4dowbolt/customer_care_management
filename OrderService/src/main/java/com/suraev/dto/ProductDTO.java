package com.suraev.dto;

import java.math.BigDecimal;

public record ProductDTO(Integer id, String name, BigDecimal price,String category) {
}
