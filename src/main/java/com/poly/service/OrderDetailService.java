package com.poly.service;

import com.poly.dto.orderdetail.OrderDetailRequest;
import com.poly.dto.orderdetail.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getCartItemsByCardId(Integer orderDetailId);
    StringBuilder addCartItem(List<OrderDetailRequest> orderDetailRequests);
}
