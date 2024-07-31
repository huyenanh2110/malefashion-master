package com.poly.converter;

import com.poly.dto.category.CategoryRequest;
import com.poly.dto.category.CategoryResponse;
import com.poly.entity.Category;

public class CategoryConverter {
    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }
    public static Category toCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        return category;
    }


}
