package com.suraev.service;

import com.suraev.dto.DiscountRequest;
import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;
import com.suraev.exception.ProductNotFoundException;
import com.suraev.exception.UserNotFoundException;
import com.suraev.repository.OrderRepository;
import com.suraev.repository.ProductRepository;
import com.suraev.repository.UserRepository;
import com.suraev.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService  {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final RestClient restClient;


    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public Optional<OrderDTO> getOrderById(Integer id) {
        return Optional.empty();
    }


    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        final var userId = orderDTO.userId();
        final var productID = orderDTO.productId();

        if(!isUserExists(userId)) throw new UserNotFoundException("User not found", HttpStatus.BAD_REQUEST);
        if(!isProductExists(productID)) throw new ProductNotFoundException("Product not found", HttpStatus.BAD_REQUEST);

        final var discountRequest = mapUserIdAndProductIdToDiscountRequest(userId, productID);

        final var discountValue = sendRequestAndGetDiscountValue(discountRequest);

        var order = OrderMapper.INSTANCE.toOrder(orderDTO);

        final var productPriceWithoutDiscount = discountRequest.getPrice();

        final var finalPriceOfProduct = calculateFinalPriceOfProduct(productPriceWithoutDiscount, discountValue);

        order.setTotalPrice(finalPriceOfProduct);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.INSTANCE.toDto(savedOrder);
    }

    private BigDecimal calculateFinalPriceOfProduct(BigDecimal totalPrice,BigDecimal discount) {

        if(discount == null) {
            return  totalPrice;
        }
        return totalPrice.subtract(discount);
    }

    private BigDecimal sendRequestAndGetDiscountValue(DiscountRequest discountRequest) {
        return restClient.post().contentType(MediaType.APPLICATION_JSON)
                .body(discountRequest)
                .retrieve()
                .body(BigDecimal.class);
    }


    private DiscountRequest mapUserIdAndProductIdToDiscountRequest(Integer userId, Integer productId) {
        final var user = userRepository.findById(userId).get();
        final var product = productRepository.findById(productId).get();

        return DiscountRequest.builder().userType(user.getType())
                .productCategory(product.getCategory())
                .price(product.getPrice()).build();
    }

    private boolean isProductExists(Integer productID) {
        return productRepository.existsById(productID);
    }

    private boolean isUserExists(Integer userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        return false;
    }
}
