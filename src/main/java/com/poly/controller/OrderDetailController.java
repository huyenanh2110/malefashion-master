package com.poly.controller;

import com.poly.dto.orderdetail.OrderDetailRequest;
import com.poly.dto.orderdetail.OrderDetailResponse;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-details")
@CrossOrigin(origins = "http://localhost:8081")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDetailResponse>> getCartItemsByCardId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(orderDetailService.getCartItemsByCardId(id));
    }

    @PostMapping
    public ResponseEntity<?> createCartItem(@RequestBody List<OrderDetailRequest> orderDetailRequestList) {
        StringBuilder badResult = orderDetailService.addCartItem(orderDetailRequestList);
        return ResponseEntity.ok().body(badResult);
    }


}
