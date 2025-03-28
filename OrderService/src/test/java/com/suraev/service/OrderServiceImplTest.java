package com.suraev.service;

import com.suraev.dto.OrderDTO;
import com.suraev.exception.UserNotFoundException;
import com.suraev.repository.OrderRepository;
import com.suraev.repository.ProductRepository;
import com.suraev.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    static OrderService orderServiceImpl;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        orderServiceImpl = new OrderServiceImpl();
    }

    @Nested
    public class createOrder {

        @Test
        public void throwExceptionNotExistedUser() {
            //given
            OrderDTO orderDTO = new OrderDTO(1,2);
            //when
            Mockito.when(userRepository.existsById(orderDTO.userId())).thenReturn(false);
            //then

            assertThrows(UserNotFoundException.class, () -> orderServiceImpl.createOrder(orderDTO));
        }

        @Test
        public void createOrderSuccess() {



        }

    }

}