package com.suraev.util;

import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;
import com.suraev.entity.User;
import com.suraev.entity.enums.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    @Test
    public void shouldMapToOrder() {
        //given
        OrderDTO orderDTO = new OrderDTO(1,1);
        //when
        Order actualResult = OrderMapper.INSTANCE.toOrder(orderDTO);
        //then
        assertAll(() -> assertThat(actualResult.getUser().getId()).isEqualTo(orderDTO.userId()),
                  () -> assertThat(actualResult.getProduct().getId()).isEqualTo(orderDTO.productId())
                );
    }

    @Test
    public void shouldMapToOrderDTO() {
        //given
        Order order = Order.builder().id(1).user(new User(1,"Denis", UserType.INDIVIDUAL)).build();
        //when
        OrderDTO actualResult = OrderMapper.INSTANCE.toDto(order);
        //then
        assertThat(actualResult.userId()).isEqualTo(order.getUser().getId());
    }

}