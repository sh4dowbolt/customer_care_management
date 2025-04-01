package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.OrderDTO;
import com.suraev.dto.ProductDTO;
import com.suraev.entity.Order;
import com.suraev.exception.ProductNotFoundException;
import com.suraev.exception.UserNotFoundException;
import com.suraev.repository.OrderRepository;
import com.suraev.repository.ProductRepository;
import com.suraev.repository.UserRepository;
import com.suraev.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public Optional<OrderDTO> getOrderById(Integer id) {
        return Optional.empty();
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        final var productID = orderDTO.productId();
        final var userId = orderDTO.userId();

        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found", HttpStatus.BAD_REQUEST);
        }
        if(!productRepository.existsById(productID)) {
            throw new ProductNotFoundException("Product not found");
        }
        final var user = userRepository.findById(userId).get();
        final var product = productRepository.findById(productID).get();

        DiscountRequest.builder().userType(user.getType())
                .productCategory(product.getCategory())
                .price(product.getPrice());



        Order order = OrderMapper.INSTANCE.toOrder(orderDTO);

        Order orderFromDB = orderRepository.save(order);

        return OrderMapper.INSTANCE.toDto(orderFromDB);
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        return false;
    }
}
