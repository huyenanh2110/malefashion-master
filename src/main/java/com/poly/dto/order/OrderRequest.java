package com.poly.dto.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poly.entity.Account;
import com.poly.entity.OrderDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class OrderRequest {
    private String address;
    private Date createDate;
    private Account account;
    private OrderDetail orderDetails;

}
