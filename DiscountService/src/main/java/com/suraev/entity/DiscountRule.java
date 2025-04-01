package com.suraev.entity;

import com.suraev.entity.enums.DiscountType;
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

    @Column(name = "product_category", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductCategory productCategory;

    @Column(name = "min_order_amount",precision = 10, scale = 2)
    private BigDecimal minOrderPrice;

    @Column(name = "discount_value")
    private BigDecimal discountValue;

    @Column(name = "discount_type", nullable = false, length = 20)
    private DiscountType discountType;
    @Column(name = "create_date")
    private Instant createdAt;

}
