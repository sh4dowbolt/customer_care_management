package com.suraev.service;

import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Integer id);

    ProductDTO createProduct(ProductDTO productDTO);

    boolean deleteProduct(Integer id);

}
