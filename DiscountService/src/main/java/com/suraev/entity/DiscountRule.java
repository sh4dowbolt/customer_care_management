package com.suraev.entity;

import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "discount_rules")
public class DiscountRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dr_id", nullable = false)
    private Integer id;

    @Column(name = "user_type", nullable = false, length = 75)
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Column(name = "product_category")
    @Enumerated(value = EnumType.STRING)
    private ProductCategory productCategory;

    @Column(name = "min_order_amount")
    private BigDecimal minOrderPrice;

    @Column(name = "max_order_amount")
    private BigDecimal maxOrderPrice;

    @Column(name = "discount_value")
    private BigDecimal discountValue;
    @Column(name = "create_date")
    private Instant createdAt;

}
