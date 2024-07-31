package com.poly.converter;

import com.poly.dto.product.ProductResponse;
import com.poly.entity.Product;

public class ProductConverter {
    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .description(product.getDescription())
                .category(product.getCategory())
                .build();
    }
}
