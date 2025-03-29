package com.suraev.service;

import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;
import com.suraev.entity.Product;
import com.suraev.entity.User;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
            var orderDTO = getOrderDTO();
            //when
            Mockito.when(userRepository.existsById(orderDTO.userId())).thenReturn(false);
            //then
            assertThrows(UserNotFoundException.class, () -> orderServiceImpl.createOrder(orderDTO));
        }

        @Test
        public void throwExceptionNotExistedProduct() {
            //given
            var orderDTO = getOrderDTO();
            //when
            Mockito.when(userRepository.existsById(orderDTO.userId())).thenReturn(true);
            Mockito.when(productRepository.existsById(orderDTO.productId())).thenReturn(false);
            //then
            assertThrows(ProductNotFoundException.class, () -> orderServiceImpl.createOrder(orderDTO));
        }
        @Test
        public void setPriceAndSave() {
            //given
            var orderDTO = getOrderDTO();
            BigDecimal totalPriceToSet= BigDecimal.valueOf(100);
            Optional<User> user = Optional.of(User.builder().id(1).name("Vitaly").type(UserType.CASUAL).build());
            Optional<Product> product = Optional.of(Product.builder().id(1).price(new BigDecimal(100)).category("casual").build());
            Order order = new Order(1,user.get(),product.get(),totalPriceToSet, Instant.now());

            //when
            Mockito.when(userRepository.existsById(orderDTO.userId())).thenReturn(true);
            Mockito.when(productRepository.existsById(orderDTO.productId())).thenReturn(true);


            Mockito.when(userRepository.findById(orderDTO.userId())).thenReturn(user);
            Mockito.when(productRepository.findById(orderDTO.productId())).thenReturn(product);


            Mockito.doReturn(order).when(orderRepository).save(ArgumentMatchers.any(Order.class));


            //then
            OrderDTO actualResult = orderServiceImpl.createOrder(orderDTO);

            assertNotNull(actualResult);

        }

        private static OrderDTO getOrderDTO() {

            return new OrderDTO(1,1);
        }



    }

}