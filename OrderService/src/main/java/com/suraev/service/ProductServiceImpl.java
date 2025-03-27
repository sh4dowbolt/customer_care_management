package com.suraev.service;

import com.suraev.dto.ProductDTO;
import com.suraev.entity.Product;
import com.suraev.repository.ProductRepository;
import com.suraev.util.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {

        return productRepository.findAll().stream()
                .map(ProductMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Integer id) {
      return productRepository.findById(id).map(ProductMapper.INSTANCE::toDto);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        final var productToSave=ProductMapper.INSTANCE.toProduct(productDTO);
        Product productFromDB = productRepository.save(productToSave);

        return ProductMapper.INSTANCE.toDto(productFromDB);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        if(productRepository.existsById(id)) {

            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
