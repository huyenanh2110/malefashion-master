package com.poly.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String productName;
    private Integer quantity;
    private Double price;
    private Double weight;
    private String photo;
    private String description;
    private Integer categoryId;
}
