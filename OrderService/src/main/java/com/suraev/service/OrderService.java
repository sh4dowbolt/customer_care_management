package com.suraev.service;

import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    Optional<OrderDTO> getOrderById(Integer id);

    Order createOrder(OrderDTO orderDTO);

    boolean deleteOrder(Integer orderId);
}
