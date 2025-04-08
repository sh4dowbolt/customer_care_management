package com.suraev.dto;

import lombok.Builder;

@Builder
public record OrderDTO(Integer userId, Integer productId) {

}
