package com.suraev.repository;

import com.suraev.dto.DiscountRequest;
import com.suraev.entity.DiscountRule;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface DiscountRuleRepository extends JpaRepository<DiscountRule, Integer> {
    @Query(value = "SELECT * FROM DiscountRule dr WHERE dr.userType = %:user" +
            " AND dr.productCategory = %:product AND dr.minOrderPrice <= %:price")
    Optional<DiscountRule> getDiscountAmount(@Param("user") UserType userType,
                               @Param("product") ProductCategory productCategory,
                               @Param("price") BigDecimal price);
}
