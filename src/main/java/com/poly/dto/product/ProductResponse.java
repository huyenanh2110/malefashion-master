package com.poly.dto.product;

import com.poly.entity.Category;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private String photo;
    private String description;
    private Category category;


}
