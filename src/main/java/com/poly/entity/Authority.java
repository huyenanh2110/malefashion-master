package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_name")
    private Account account;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Other properties and methods can be added here
}
