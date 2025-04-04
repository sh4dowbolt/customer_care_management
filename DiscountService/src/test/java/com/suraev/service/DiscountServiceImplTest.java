package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.DiscountResponse;
import com.suraev.entity.DiscountRule;
import com.suraev.entity.enums.DiscountType;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import com.suraev.repository.DiscountRuleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DiscountServiceImplTest {
    @Mock
    private DiscountRuleRepository discountRuleRepository;
    @InjectMocks
    private DiscountServiceImpl discountService;

    @Test
    public void shouldReturnNonNullDiscountValue() {
        // ('INDIVIDUAL', 'CLOTHES', 10000, 10, 'PERCENT')
        //given
        DiscountRequest discountRequest = new DiscountRequest(UserType.INDIVIDUAL, ProductCategory.CLOTHES,
                BigDecimal.valueOf(10000));

        DiscountRule ruleFromDB = DiscountRule.builder().discountType(DiscountType.PERCENT).userType(UserType.INDIVIDUAL)
                .discountValue(BigDecimal.valueOf(10)).productCategory(ProductCategory.CLOTHES).build();

        Optional<DiscountRule> discountRule = Optional.of(ruleFromDB);

        BigDecimal expectedDiscount = BigDecimal.valueOf(10000).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100));
        //when
        Mockito.when(discountRuleRepository.getDiscountAmount(discountRequest.userType(),discountRequest.productCategory(),
                discountRequest.price())).thenReturn(discountRule);
        //then
        DiscountResponse actualResult = discountService.calculateDiscountAmount(discountRequest);

        assertThat(actualResult.discountAmount()).isPositive();
        assertThat(actualResult.discountAmount()).isEqualByComparingTo(expectedDiscount);

    }


}