package com.poly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    // Other properties and methods can be added here
}
