package com.suraev.dto;

import com.suraev.entity.enums.UserType;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link com.suraev.entity.User}
 */
@Builder
public record UserDTO(Integer id, String name, UserType type) {
}
