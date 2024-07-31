package com.poly.service.impl;

import com.poly.converter.CategoryConverter;
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


}
