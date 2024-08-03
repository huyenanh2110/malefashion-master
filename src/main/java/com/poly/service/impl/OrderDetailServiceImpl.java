package com.poly.service.impl;

import com.poly.converter.OrderDetailConverter;
import com.poly.dto.orderdetail.OrderDetailRequest;
import com.poly.dto.orderdetail.OrderDetailResponse;
import com.poly.entity.OrderDetail;
import com.poly.entity.Product;
import com.poly.repository.OrderDetailRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    private final ProductRepository productRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductRepository productRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<OrderDetailResponse> getCartItemsByCardId(Integer cardId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrder_OrderId(cardId);
        List<OrderDetailResponse> list = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailResponse cartItemResponse = OrderDetailConverter.toCartItemResponse(orderDetail);
            list.add(cartItemResponse);
        }
        return list;
    }

    @Override
    public StringBuilder addCartItem(List<OrderDetailRequest> orderDetailRequestList) {
        StringBuilder badResult = new StringBuilder();

        for (OrderDetailRequest orderDetailRequest : orderDetailRequestList) {
          Optional<Product> product = productRepository.findById(orderDetailRequest.getProductId());
            if(orderDetailRequest.getQuantity() > product.get().getQuantity()){
                badResult.append("The quantity of " + product.get().getProductName() + " is greater than the inventory quantity (" + product.get().getQuantity() +")" + ".\n");
            }
        }
        if(badResult.length()==0){
            for (OrderDetailRequest orderDetailRequest : orderDetailRequestList) {
                orderDetailRepository.save(OrderDetailConverter.toCartItem(orderDetailRequest));
            }

        }
        return badResult;
    }
}
