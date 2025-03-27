package com.suraev.service;

import com.suraev.dto.ProductDTO;
import com.suraev.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll().stream().map();
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return false;
    }
}
