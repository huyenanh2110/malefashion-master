package com.poly.converter;

import com.poly.dto.orderdetail.OrderDetailRequest;
import com.poly.dto.orderdetail.OrderDetailResponse;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.entity.Product;

public class OrderDetailConverter {
    public static OrderDetailResponse toCartItemResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .product(orderDetail.getProduct())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .build();
    }

    public static OrderDetail toCartItem(OrderDetailRequest request){
        OrderDetail orderDetail = new OrderDetail();
        if(request.getProductId() != null){
            Product product = new Product();
            product.setProductId(request.getProductId());
            orderDetail.setProduct(product);
        }
        if(request.getQuantity() != null){
            orderDetail.setQuantity(request.getQuantity());
        }
        if(request.getPrice() != null){
            orderDetail.setPrice(request.getPrice());
        }
        if(request.getCartId() != null){
            Order order = new Order();
            order.setOrderId(request.getCartId());
            orderDetail.setOrder(order);
        }
        return orderDetail;
    }
}
