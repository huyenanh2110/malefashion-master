package com.poly.controller;


import com.poly.dto.category.CategoryRequest;
import com.poly.dto.category.CategoryResponse;
import com.poly.entity.Category;
import com.poly.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = {"Authorization"})
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<CategoryResponse>> getCategories(@RequestParam("page") Optional<Integer> page,
                                                                @RequestParam("size") Optional<Integer> size) {
        Integer count = categoryService.countCategory();
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(count));
        return ResponseEntity.ok(categoryService.getCategories(pageable));
    }
    @PostMapping
    public ResponseEntity<CategoryRequest> saveCategory(@Validated @ModelAttribute CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Category> removeCategory(@PathVariable Integer id) {
        categoryService.removeCategory(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryRequest> updateCategory(@PathVariable Integer id,
                                                          @ModelAttribute CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequest));
    }





}
