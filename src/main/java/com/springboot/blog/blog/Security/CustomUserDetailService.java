package com.springboot.blog.blog.Security;

import com.springboot.blog.blog.entities.User;
import com.springboot.blog.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","Enail" + username,0));


        return user;
    }
}
