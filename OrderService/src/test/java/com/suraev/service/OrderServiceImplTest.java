package com.suraev.service;

import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;
import com.suraev.entity.Product;
import com.suraev.entity.User;
import com.suraev.entity.enums.ProductCategory;
import com.suraev.entity.enums.UserType;
import com.suraev.exception.ProductNotFoundException;
import com.suraev.exception.UserNotFoundException;
import com.suraev.repository.OrderRepository;
import com.suraev.repository.ProductRepository;
import com.suraev.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    static OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;


    @Nested
    public class createOrder {

        @Test
        public void throwExceptionNotExistedUser() {
            //given
            var orderDTO = getOrderDTO();
            //when
            when(userRepository.existsById(orderDTO.userId())).thenReturn(false);
            //then
            assertThrows(UserNotFoundException.class, () -> orderService.createOrder(orderDTO));
        }

        @Test
        public void throwExceptionNotExistedProduct() {
            //given
            var orderDTO = getOrderDTO();
            //when
            when(userRepository.existsById(orderDTO.userId())).thenReturn(true);
            when(productRepository.existsById(orderDTO.productId())).thenReturn(false);
            //then
            assertThrows(ProductNotFoundException.class, () -> orderService.createOrder(orderDTO));
        }
        @Test
        public void setPriceAndSave() {
            //given
            var orderDTO = getOrderDTO();
            var totalPriceToSet= BigDecimal.valueOf(100);
            var user = Optional.of(User.builder().id(1).name("Vitaly").type(UserType.INDIVIDUAL).build());
            var product = Optional.of(Product.builder().id(1).price(new BigDecimal(100))
                    .category(ProductCategory.ANY).build());
            var orderToSave = new Order(1,user.get(),product.get(),totalPriceToSet, Instant.now());
            var userId = orderDTO.userId();
            var productId = orderDTO.productId();
            //when
            when(userRepository.existsById(userId)).thenReturn(true);
            when(productRepository.existsById(productId)).thenReturn(true);

            when(userRepository.findById(userId)).thenReturn(user);
            when(productRepository.findById(productId)).thenReturn(product);

            doReturn(orderToSave).when(orderRepository).save(any(Order.class));
            //then
            OrderDTO actualResult = orderService.createOrder(orderDTO);

            assertAll(
                        () -> assertNotNull(actualResult),
                        () -> assertEquals(actualResult.userId(),user.get().getId()),
                        () -> verify(orderRepository, times(1)).save(any(Order.class)),
                        () -> verify(userRepository, times(1)).findById(userId),
                        () -> verify(userRepository, times(1)).existsById(userId),
                        () -> verify(productRepository, times(1)).existsById(productId),
                        () -> verify(productRepository, times(1)).findById(productId)
            );
        }

        private static OrderDTO getOrderDTO() {

            return new OrderDTO(1,1);
        }



    }

}