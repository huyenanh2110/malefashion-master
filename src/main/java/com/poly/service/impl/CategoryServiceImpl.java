package com.poly.service.impl;

import com.poly.converter.CategoryConverter;
import com.poly.dto.category.CategoryRequest;
import com.poly.dto.category.CategoryResponse;
import com.poly.entity.Category;
import com.poly.exception.ResourceNotFoundException;
import com.poly.repository.CategoryRepository;
import com.poly.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getCategories() {
      List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Category", "list", "is empty");
        }

        return categories.stream()
                .map(CategoryConverter::toCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoryResponse> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);

        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Category", "list", "is empty");
        }
        return categories.map(category -> {
            CategoryResponse categoryResponse = CategoryConverter.toCategoryResponse(category);
            return categoryResponse;
        });
    }

    @Override
    public CategoryRequest saveCategory(CategoryRequest categoryRequest) {
        Category category = CategoryConverter.toCategory(categoryRequest);
        categoryRepository.save(category);
        return categoryRequest;
    }

    @Override
    public void removeCategory(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Category", "id", "" + id);
        }
    }

    @Override
    public Integer countCategory() {
        return Math.toIntExact(categoryRepository.count());
    }

    @Override
    public CategoryResponse getCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", "" + id));
        return CategoryConverter.toCategoryResponse(category);
    }

    @Override
    public CategoryRequest updateCategory(Integer id,
                                          CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", "" + id));

        if (categoryRequest.getCategoryName() != null) {
            category.setCategoryName(categoryRequest.getCategoryName());
        }

        categoryRepository.save(category);

        return categoryRequest;
    }

}
