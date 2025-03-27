package com.suraev.dto;

import com.suraev.entity.enums.UserType;
import lombok.Builder;

@Builder
public record UserDTO(Integer id, String name, UserType type) {

}
