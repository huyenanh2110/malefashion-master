package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "product_id")
   private Integer productId;

   @Column(name = "product_name")
   private String productName;

   @Column(name = "quantity")
   private Integer quantity;

   @Column(name = "price")
   private Double price;

   @Column(name = "photo")
   private String photo;

   @Column(name = "create_date")
   private java.sql.Date createDate;

   @Column(name = "available")
   private Boolean available;

   @Column(name = "description")
   private String description;

   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;

   @JsonManagedReference
   @OneToMany(mappedBy = "product")
   private Set<OrderDetail> orderDetails;

    public void setCreateDate(LocalDateTime now) {
    }

    // Other properties and methods can be added here
}
