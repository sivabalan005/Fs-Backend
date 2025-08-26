package com.ecom.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecom.entity.UserEntity;
import com.ecom.orderservice.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Signup API
    @PostMapping("/signup")
    public String signup(@RequestBody UserEntity userEntity) {
        try {
            userService.signup(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword());
            return "User registered successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Login API
    @PostMapping("/login")
    public String login(@RequestBody UserEntity userEntity) {
        try {
            userService.login(userEntity.getUsername(), userEntity.getPassword()); // Username or email can be passed here
            return "Login successful!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
