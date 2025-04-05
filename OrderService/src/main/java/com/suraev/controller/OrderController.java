package com.suraev.controller;

import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;
import com.suraev.service.OrderService;
import com.suraev.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderServiceImpl;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {

        OrderDTO order = orderServiceImpl.createOrder(orderDTO);

        return new ResponseEntity<>(OrderMapper.INSTANCE.toOrder(order), HttpStatus.OK);
    }
}
