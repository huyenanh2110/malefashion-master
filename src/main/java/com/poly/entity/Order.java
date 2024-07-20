package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer id;

    @Column(name = "address")
    String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    Date createDate = new Date();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_name")
    private Account account;

    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;
}
