package com.poly.dto.orderdetail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequest {
    private Integer quantity;
    private Double price;
    private Integer productId;
    private Integer cartId;
}
