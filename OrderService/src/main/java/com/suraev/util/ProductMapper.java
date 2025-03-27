package com.suraev.util;

import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

   ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);

   Product toProduct(ProductDTO productDTO);

   ProductDTO toDto(Product product);
}
