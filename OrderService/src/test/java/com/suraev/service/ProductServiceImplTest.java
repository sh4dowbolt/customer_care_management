package com.suraev.service;

import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;
import com.suraev.repository.ProductRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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
            assertThat(actualResult).extracting("id","name")
                    .containsExactlyInAnyOrderElementsOf(products.stream().map(x-> tuple(x.getId(), x.getName()))
                            .toList());
        }
    }

    @Nested
    public class getProductById {

        @Test
        public void getProductIfExist() {
            //given
            Integer existedProductId=1;
            Product product= new Product(1,"Dmitry", new BigDecimal(100), "usual");

            //when
            Mockito.when(productRepository.findById(existedProductId)).thenReturn(Optional.of(product));
            // then
            Optional<ProductDTO> actualResult = productService.getProductById(existedProductId);

            assertThat(actualResult).isPresent();
            assertThat(actualResult.get().id()).isEqualTo(existedProductId);
        }

        @Test
        public void getProductIfNotExist() {
            //given
            Integer notExistedProductId=2;
            //when
            Mockito.when(productRepository.findById(notExistedProductId)).thenReturn(Optional.empty());
            // then
            var actualResult = productService.getProductById(notExistedProductId);

            assertThat(actualResult).isNotPresent();

        }

    @Nested
    public class createProduct {

        @Test
        public void create() {
            //given
            ProductDTO productDTO = new ProductDTO(2, "Dmitry",new BigDecimal(100),"usual");
            Product product = new Product(2, "Dmitry",new BigDecimal(100),"usual");
            Product newProductFromDB = new Product(3, "Dmitry",new BigDecimal(100),"usual");
            //when
            Mockito.when(productRepository.save(product)).thenReturn(newProductFromDB);
            //then
            ProductDTO actualResult = productService.createProduct(productDTO);

            assertThat(actualResult).extracting("name").isEqualTo(newProductFromDB.getName());
        }


    }





    }

}