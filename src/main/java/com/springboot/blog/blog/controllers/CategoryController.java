package com.springboot.blog.blog.controllers;

import com.springboot.blog.blog.payloads.ApiResponse;
import com.springboot.blog.blog.payloads.CategoryDto;
import com.springboot.blog.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully.", true),HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("categoryById/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }
}
