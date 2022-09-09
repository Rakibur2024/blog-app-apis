package com.springboot.blog.blog.services;

import com.springboot.blog.blog.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId);

    //delete
    void deleteCategory(Integer categoryId);

    //get by id
    CategoryDto getCategoryById(Integer categoryId);

    //Get ALl Categoris
    List<CategoryDto> getCategories();
}
