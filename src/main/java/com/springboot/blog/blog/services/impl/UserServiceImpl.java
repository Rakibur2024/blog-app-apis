package com.springboot.blog.blog.services.impl;

import com.springboot.blog.blog.Configuration.AppConstants;
import com.springboot.blog.blog.entities.Role;
import com.springboot.blog.blog.entities.User;
import com.springboot.blog.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.blog.payloads.UserDto;
import com.springboot.blog.blog.repositories.RoleRepo;
import com.springboot.blog.blog.repositories.UserRepo;
import com.springboot.blog.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        //encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //roles
        Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);

        User newUser = userRepo.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        //user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);
        UserDto updatedUserDto = userToDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        return user;
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
//        return userDto;
    }
}
