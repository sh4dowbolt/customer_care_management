package com.suraev.dto;

import lombok.Builder;

@Builder
public record OrderDTO(Integer ordrId, Integer userId, Integer productId) {

}
