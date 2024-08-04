package com.poly.converter;

import com.poly.dto.product.ProductRequest;
import com.poly.dto.product.ProductResponse;
import com.poly.entity.Category;
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

    public static Product toProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
//        product.setPhoto(productRequest.getPhoto());


        Category category = new Category();
        category.setCategoryId(productRequest.getCategoryId());
        product.setCategory(category);
        return product;
    }

}
