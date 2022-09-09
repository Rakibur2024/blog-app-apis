package com.springboot.blog.blog.repositories;

import com.springboot.blog.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
