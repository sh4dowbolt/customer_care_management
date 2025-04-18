package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServiceClientImplTest {

    @InjectMocks
    DiscountServiceClientImpl discountServiceClient;

    @Mock
    RestClient restClient;
    @Mock
    RestClient.ResponseSpec responseSpec;
    @Mock
    RestClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    RestClient.RequestBodySpec requestBodySpec;

    @BeforeEach
    void setUp() {
        when(restClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.contentType(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(DiscountRequest.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    public void getDiscountValue() {

        //given
        DiscountRequest discountRequest = DiscountRequest.builder().userType(UserType.VIP)
                .productCategory(ProductCategory.ELECTRONIC).price(BigDecimal.valueOf(1)).build();
        BigDecimal discountValue = BigDecimal.valueOf(1);
        //when
        when(responseSpec.body(BigDecimal.class)).thenReturn(discountValue);
        //then
        BigDecimal actualResult = discountServiceClient.getDiscountValue(discountRequest);

        assertThat(actualResult).isEqualTo(discountValue);
    }

}