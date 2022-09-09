package com.springboot.blog.blog.services.impl;

import com.springboot.blog.blog.entities.Category;
import com.springboot.blog.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.blog.payloads.CategoryDto;
import com.springboot.blog.blog.payloads.UserDto;
import com.springboot.blog.blog.repositories.CategoryRepo;
import com.springboot.blog.blog.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto,Category.class);
        Category addedCategory = categoryRepo.save(category);
        return modelMapper.map(addedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId) {
        Category category = categoryRepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",CategoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        categoryRepo.save(category);
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> categoryToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto,Category.class);
        return category;
    }

    public CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
