package com.springboot.blog.blog.repositories;

import com.springboot.blog.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByIdAndName(Integer normalUser, String normal_user);
}
