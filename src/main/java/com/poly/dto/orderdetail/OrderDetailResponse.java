package com.poly.dto.orderdetail;

import com.poly.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private Product product;
    private Integer quantity;
    private Double price;
}
