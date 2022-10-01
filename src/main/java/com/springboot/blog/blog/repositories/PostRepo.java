package com.springboot.blog.blog.repositories;

import com.springboot.blog.blog.entities.Category;
import com.springboot.blog.blog.entities.Post;
import com.springboot.blog.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
