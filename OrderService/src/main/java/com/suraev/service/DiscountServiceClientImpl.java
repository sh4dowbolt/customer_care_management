package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
public class DiscountServiceClientImpl implements DiscountServiceClient {

    private final RestClient.Builder restClient;

    private final String discountServiceUrl;

    public DiscountServiceClientImpl(RestClient.Builder restClient,
                                     @Value("${discount.service.url}")String discountServiceUrl) {
        this.restClient = restClient;
        this.discountServiceUrl = discountServiceUrl;
    }

    @Override
    public BigDecimal getDiscountValue(DiscountRequest discountRequest) {
        RestClient restClient1= RestClient.builder().baseUrl(discountServiceUrl).build();
        return restClient1.post()
                .uri("/api/v1/getDiscount")
                .contentType(MediaType.APPLICATION_JSON)
                .body(discountRequest)
                .retrieve()
                .body(BigDecimal.class);
    }
}
