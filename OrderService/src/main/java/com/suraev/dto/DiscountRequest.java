package com.suraev.dto;

import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest {

    private UserType userType;

    private ProductCategory productCategory;

    private BigDecimal price;
}
