package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.DiscountResponse;
import com.suraev.entity.DiscountRule;
import com.suraev.entity.enums.DiscountType;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import com.suraev.repository.DiscountRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService{

    private final DiscountRuleRepository discountRuleRepository;

    @Override
    public DiscountResponse calculateDiscountAmount(DiscountRequest discountRequest) {
        final var price = discountRequest.price();
        final var productCategory = discountRequest.productCategory();
        final var userType = discountRequest.userType();
        return discountRuleRepository.
                getDiscountAmount(userType, productCategory, price)
                .map(rule -> {
                    BigDecimal discount =rule.getDiscountType().equals(DiscountType.PERCENT)
                            ? price.multiply(rule.getDiscountValue()).divide(BigDecimal.valueOf(100)) : rule.getDiscountValue();
                    return new DiscountResponse(discount);
                }).orElse(new DiscountResponse(BigDecimal.ZERO));
    }

}
