package com.poly.service;

import com.poly.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductResponse> getProducts(Pageable pageable);



    Long countProducts();

    List<ProductResponse> getFeatureProducts();
}
