package com.suraev.util;

import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    public void mapToProduct() {
        //given
        ProductDTO productDTO = new ProductDTO(1,"Viktor",new BigDecimal(100),"conquest");
        //when
        Product actualResult = ProductMapper.INSTANCE.toProduct(productDTO);
        //then
        assertThat(actualResult.getId()).isEqualTo(productDTO.id());
    }
    @Test
    public void mapToProductDTO() {
        //given
        Product product = new Product(1,"Viktor",new BigDecimal(100),"conquest");
        //when
        ProductDTO actualResult = ProductMapper.INSTANCE.toDto(product);
        //then
        assertThat(actualResult.id()).isEqualTo(product.getId());
    }

}