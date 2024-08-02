package com.poly.service.impl;

import com.poly.converter.CategoryConverter;
import com.poly.converter.ProductConverter;
import com.poly.dto.product.ProductResponse;
import com.poly.entity.Product;
import com.poly.exception.ResourceNotFoundException;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductResponse> getFeatureProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product", "list", "empty");

        }
        return products.stream().map(ProductConverter::toProductResponse)
                .collect(Collectors.toList());

    }

    @Override
    public Page<ProductResponse> getAllProductsByCategory(Integer category, int page, int size, String sort) {
        Sort sorting = sort.equalsIgnoreCase("asc")
                ? Sort.by(Sort.Direction.ASC, "price")
                : Sort.by(Sort.Direction.DESC, "price");
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<Product> products = productRepository.findProductByCategoryId(category, pageable);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product", "list", "empty");
        }
        return products.map(product -> {
            ProductResponse productResponse = ProductConverter.toProductResponse(product);
            return productResponse;
        });
    }
}
