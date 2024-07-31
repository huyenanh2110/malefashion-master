package com.poly.service.impl;

import com.poly.converter.ProductConverter;
import com.poly.dto.product.ProductResponse;
import com.poly.entity.Product;
import com.poly.exception.ResourceNotFoundException;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Page<ProductResponse> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product", "list", "empty");
        }

        return products.map(product -> {
            ProductResponse productResponse = ProductConverter.toProductResponse(product);
            return productResponse;
        });
    }

    @Override
    public Long countProducts() {
        return productRepository.count();
    }
}
