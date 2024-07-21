package com.poly.dto.product;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private String photo;
    private String description;
}
