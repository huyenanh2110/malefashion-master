package com.poly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String photo;
    @Column(name = "is_active")
    private boolean activated;
    private boolean admin;
    @OneToMany(mappedBy = "account")
    List<Order> orders;
}
