package com.springboot.blog.blog.controllers;

import com.springboot.blog.blog.Security.JwtTokenHelper;
import com.springboot.blog.blog.exceptions.ApiException;
import com.springboot.blog.blog.payloads.JwtAuthRequest;
import com.springboot.blog.blog.payloads.JwtAuthResponse;
import com.springboot.blog.blog.payloads.UserDto;
import com.springboot.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken( @RequestBody JwtAuthRequest request ) throws Exception {
        authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e){
            System.out.println("Invalid username or password");
            throw new ApiException("Invalid username or password");
        }
    }

    //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
       UserDto registeredUser =  userService.registerNewUser(userDto);
       return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }

}
