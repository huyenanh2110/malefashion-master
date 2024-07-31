package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "address")
    private String address;

    @Column(name = "create_date")
    private Date createDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_name")
    private Account account;

    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    // Other properties and methods can be added here
}
