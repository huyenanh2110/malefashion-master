package com.poly.service;

import com.poly.dto.category.CategoryRequest;
import com.poly.dto.category.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getCategories();

    Page<CategoryResponse> getCategories(Pageable pageable);

    CategoryRequest saveCategory(CategoryRequest categoryRequest);

    void removeCategory(Integer id);
    Integer countCategory();

    CategoryResponse getCategory(Integer id);

    CategoryRequest updateCategory(Integer id, CategoryRequest categoryRequest);
}
