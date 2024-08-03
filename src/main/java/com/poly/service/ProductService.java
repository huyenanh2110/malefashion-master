package com.poly.service;

import com.poly.dto.product.ProductRequest;
import com.poly.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Page<ProductResponse> getProducts(Pageable pageable);


    Long countProducts();

    List<ProductResponse> getFeatureProducts();

    Page<ProductResponse> getAllProductsByCategory(Integer category, int page, int size,
                                                   String sort);

    Integer saveProduct(ProductRequest productRequest, MultipartFile file);


    void removeProduct(Integer id);

    ProductRequest updateProduct(Integer id, ProductRequest productRequest, MultipartFile file);

    ProductResponse getProduct(Integer id);


}
