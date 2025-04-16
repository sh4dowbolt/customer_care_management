package com.suraev.service;

import com.suraev.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    Optional<ProductDTO> getProductById(Integer id);

    ProductDTO createProduct(ProductDTO productDTO);

    boolean deleteProduct(Integer id);

}
