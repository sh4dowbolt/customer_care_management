package com.suraev.repository;

import com.suraev.entity.DiscountRule;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface DiscountRuleRepository extends JpaRepository<DiscountRule, Integer> {
    @Query(value = "SELECT dr  FROM DiscountRule dr WHERE dr.userType = :userType " +
            "AND dr.productCategory = :productCategory AND dr.minOrderPrice <= :price")
    Optional<DiscountRule> getDiscountAmount(@Param("userType") UserType userType,
                               @Param("productCategory") ProductCategory productCategory,
                               @Param("price") BigDecimal price);
}
