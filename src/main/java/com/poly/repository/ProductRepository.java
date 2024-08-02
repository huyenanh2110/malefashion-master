package com.poly.repository;

import com.poly.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT o FROM Product o WHERE :categoryId is null or o.category.categoryId= :categoryId")
    Page<Product> findProductByCategoryId(@Param("categoryId") Integer categoryId, Pageable pageable);
}
