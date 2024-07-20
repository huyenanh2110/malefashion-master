package com.poly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "is_active")
    private boolean activated;

    @Column(name ="admin")
    private boolean admin;

    @OneToMany(mappedBy = "account")
    Set<Order> orders;
}
