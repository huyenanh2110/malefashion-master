package com.poly.dto.order;

import com.poly.entity.Account;
import com.poly.entity.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class OrderResponse {
    private String orderId;
    private OrderDetail orderDetail;
    private Account account;
    private String address;
    private Date createDate;

}
