package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.registerUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("user", savedUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User dbUser = userService.getByEmail(user.getEmail());
        Map<String, Object> response = new HashMap<>();

        if (dbUser == null) {
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Check password
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            response.put("error", "Invalid credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(dbUser.getEmail()); // Single argument

        response.put("token", token);
        response.put("userId", dbUser.getId());
        response.put("email", dbUser.getEmail());
        response.put("role", dbUser.getRole());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
