package com.springboot.blog.blog.controllers;

import com.springboot.blog.blog.payloads.ApiResponse;
import com.springboot.blog.blog.payloads.UserDto;
import com.springboot.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
        UserDto updatedUser = userService.updateUser(userDto,uid);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        userService.deleteUser(uid);
        return new ResponseEntity(new ApiResponse("Category Deleted Successfully.", true),HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("userById/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
