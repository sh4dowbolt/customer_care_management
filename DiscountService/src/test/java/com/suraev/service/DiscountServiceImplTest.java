package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.DiscountResponse;
import com.suraev.entity.DiscountRule;
import com.suraev.entity.enums.DiscountType;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import com.suraev.repository.DiscountRuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DiscountServiceImplTest {
    @Mock
    private DiscountRuleRepository discountRuleRepository;
    @InjectMocks
    private  DiscountServiceImpl discountService;

    @Test
    public void shouldReturnPercentDiscountValue() {
        // ('INDIVIDUAL', 'CLOTHES', 10000, 10, 'PERCENT')
        //given
        DiscountRequest discountRequest = getPercentDiscountRequest();

        DiscountRule ruleFromDB = getPersonDiscountRule();

        Optional<DiscountRule> discountRule = Optional.of(ruleFromDB);

        BigDecimal expectedDiscount = getDiscountValueEqualOneThousand();
        //when
        Mockito.when(discountRuleRepository.getDiscountAmount(discountRequest.userType(),
                discountRequest.productCategory(),
                discountRequest.price())).thenReturn(discountRule);
        //then
        DiscountResponse actualResult = discountService.calculateDiscountAmount(discountRequest);

        assertThat(actualResult.discountAmount()).isEqualByComparingTo(expectedDiscount);
    }

    @Test
    public void shouldReturnFixedDiscountValue() {
        //('JURIDICAL', 'ANY', 20000, 3000, 'FIXED')
        //given
        DiscountRequest discountRequest = getFixedDiscountRequest();

        DiscountRule discountRule = getFixedDiscountRule();

        Optional<DiscountRule> optionalDiscountRule = Optional.of(discountRule);

        BigDecimal expectedDiscount = BigDecimal.valueOf(3000);
        //when
        Mockito.when(discountRuleRepository.getDiscountAmount(discountRequest.userType(),
                discountRequest.productCategory(),discountRequest.price())).thenReturn(optionalDiscountRule);
        //then
        DiscountResponse actualResult = discountService.calculateDiscountAmount(discountRequest);

        assertThat(actualResult.discountAmount()).isEqualByComparingTo(expectedDiscount);

    }
    @Test
    public void shouldReturnNounDiscountValueIfNotRightDiscountRule() {
        // ('VIP', 'ELECTRONIC', 5000, 20, 'PERCENT')
        //given
        DiscountRequest discountRequest = getDiscountForTooSmallPrice();

        Optional<DiscountRule> notExistedDiscountRule = Optional.empty();
        //when
        Mockito.when(discountRuleRepository.getDiscountAmount(discountRequest.userType(),discountRequest.productCategory(),
                discountRequest.price())).thenReturn(notExistedDiscountRule);
        //then
        DiscountResponse actualResult = discountService.calculateDiscountAmount(discountRequest);

        assertThat(actualResult.discountAmount()).isZero();

    }

    private static DiscountRequest getDiscountForTooSmallPrice() {
        return new DiscountRequest(UserType.VIP, ProductCategory.ELECTRONIC, BigDecimal.valueOf(1000));
    }

    private static DiscountRule getFixedDiscountRule() {
        return DiscountRule.builder().discountValue(BigDecimal.valueOf(3000)).discountType(DiscountType.FIXED)
                .userType(UserType.JURIDICAL).build();
    }

    private static DiscountRequest getFixedDiscountRequest() {
        return new DiscountRequest(UserType.JURIDICAL, ProductCategory.ANY, BigDecimal.valueOf(50000));
    }


    private static BigDecimal getDiscountValueEqualOneThousand() {
        return BigDecimal.valueOf(10000).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100));
    }

    private static DiscountRule getPersonDiscountRule() {
        return DiscountRule.builder().discountType(DiscountType.PERCENT).userType(UserType.INDIVIDUAL)
                .discountValue(BigDecimal.valueOf(10)).productCategory(ProductCategory.CLOTHES).build();
    }
    private static DiscountRequest getPercentDiscountRequest() {
        return new DiscountRequest(UserType.INDIVIDUAL, ProductCategory.CLOTHES,
                BigDecimal.valueOf(10000));
    }


}