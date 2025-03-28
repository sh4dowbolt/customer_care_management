package com.suraev.util;

import com.suraev.dto.OrderDTO;
import com.suraev.entity.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderMapper INSTANCE=Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    Order toOrder(OrderDTO orderDTO);

    @InheritInverseConfiguration(name = "toOrder")
    OrderDTO toDto(Order order);
}
