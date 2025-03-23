package com.suraev.dto;

import com.suraev.entity.enums.UserType;
import lombok.Builder;

/**
 * DTO for {@link com.suraev.entity.User}
 */
@Builder
public record UserDTO(String name, UserType type) {
}
