package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "admin")
    private boolean admin;

    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    private Set<Authority> authorities;

    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    private Set<Order> orders;

    // Other properties and methods can be added here
}
