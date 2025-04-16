package com.suraev.service;

import com.suraev.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    Optional<OrderDTO> getOrderById(Integer id);

    OrderDTO createOrder(OrderDTO orderDTO);

    boolean deleteOrder(Integer orderId);
}
