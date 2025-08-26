package com.ecom.orderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.UserEntity;
import com.ecom.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Signup logic with username
    public UserEntity signup(String username, String email, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists.");
        }
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already exists.");
        }
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);  // Ideally, hash the password before saving
        return userRepository.save(newUser);
    }

    // Login logic with either username or email
    public UserEntity login(String usernameOrEmail, String password) {
        UserEntity userEntity = userRepository.findByUsername(usernameOrEmail);
        if (userEntity == null) {
            userEntity = userRepository.findByEmail(usernameOrEmail); // Try finding by email if username fails
        }
        
        if (userEntity == null || !userEntity.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials.");
        }
        return userEntity;
    }
}
