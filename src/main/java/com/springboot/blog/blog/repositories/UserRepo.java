package com.springboot.blog.blog.repositories;

import com.springboot.blog.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
