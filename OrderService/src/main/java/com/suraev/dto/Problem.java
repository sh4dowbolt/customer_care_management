package com.suraev.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public class Problem {

    private String title;
    private String status;
    private Integer code;
    private String  message;
    private Instant generatedAt;

}
