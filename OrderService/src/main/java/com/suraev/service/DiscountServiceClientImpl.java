package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DiscountServiceClientImpl implements DiscountServiceClient {

    private final RestClient restClient;

    @Override
    public BigDecimal getDiscountValue(DiscountRequest discountRequest) {
        return restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(discountRequest)
                .retrieve()
                .body(BigDecimal.class);
    }
}
