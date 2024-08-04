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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
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
        // Check and save category
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "categoryId",
                        "" + productRequest.getCategoryId()));

        // Save image
        String imagePath = saveImage(file);

        // Convert and save product
        Product product = ProductConverter.toProduct(productRequest);
        product.setCreateDate(LocalDateTime.now());
        product.setPhoto(imagePath); // Set image path to product
        productRepository.save(product);

        return product.getProductId();
    }

    private String saveImage(MultipartFile file) {
        try {
            // Define the upload directory
            String uploadDir = "src/main/resources/static/user/img/clothes/";
            Path uploadPath = Paths.get(uploadDir);

            // Ensure the directory exists, create it if it doesn't
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Create the full file path
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Save the file to the directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the image path
            return  fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not save image: " + e.getMessage());
        }
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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", "" + id));
        ProductResponse productResponse = ProductConverter.toProductResponse(product);
        return productResponse;
    }
}
