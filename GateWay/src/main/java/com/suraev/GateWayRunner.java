package com.suraev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayRunner {
    public static void main(String[] args) {
        SpringApplication.run(GateWayRunner.class, args);
    }
}