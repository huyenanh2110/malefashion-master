package com.poly.service.impl;

import com.poly.converter.CategoryConverter;
import com.poly.converter.ProductConverter;
import com.poly.dto.product.ProductRequest;
import com.poly.dto.product.ProductResponse;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.exception.ResourceNotFoundException;
import com.poly.repository.CategoryRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    @Override
    public Integer saveProduct(ProductRequest productRequest, MultipartFile file) {
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "categoryId",
                        "" + productRequest.getCategoryId()));
        Product product = ProductConverter.toProduct(productRequest);
        productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public void removeProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Product ", "id", "" + id);
        }
    }

    @Override
    public ProductRequest updateProduct(Integer id, ProductRequest productRequest, MultipartFile file) {
        return null;
    }

    @Override
    public ProductResponse getProduct(Integer id) {
        return null;
    }
}
