package com.poly.controller;

import com.poly.dto.product.ProductResponse;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin (origins = "http://localhost:8081")
public class ProductController {
    private final ProductService productServiceImpl;
    private final ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> getProducts(@RequestParam("page") Optional<Integer> page,
                                                             @RequestParam("size") Optional<Integer> size) {
        Integer count = Math.toIntExact(productServiceImpl.countProducts());
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(count));
        Page<ProductResponse> productResponses = productServiceImpl.getProducts(pageable);
        return ResponseEntity.ok(productResponses);
    }
    @GetMapping("/feature-product")
    public ResponseEntity<List<ProductResponse>> getFeatureProducts() {
        return ResponseEntity.ok(productServiceImpl.getFeatureProducts());
    }
}
