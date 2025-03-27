package com.suraev.service;

import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;
import com.suraev.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    static ProductService productService;
    @Mock
    ProductRepository productRepository;

    @BeforeAll
    public static void prepareProductService() {
        productService= new ProductServiceImpl();
    }

    @Nested
    public class getAllProducts {

        @Test
        public void getAll() {
            //given
            List<Product> products = List.of(new Product(1,"Vitaly",new BigDecimal(100), "usual"),
                                            new Product(2,"Dmitry",new BigDecimal(10), "usual"));
            //when
            Mockito.when(productRepository.findAll()).thenReturn(products);
            //then
            List<ProductDTO> actualResult = productService.getAllProducts();

            assertThat(actualResult).hasSameSizeAs(products);
        }

    }

}