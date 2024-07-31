package com.poly.service;

import com.poly.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> getProducts(Pageable pageable);

    Long countProducts();
}
