package com.poly.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String productName;
    private Integer quantity;
    private Double price;
    private String description;
    private Integer categoryId;
}
