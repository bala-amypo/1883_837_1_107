package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Fetch user by email
        User dbUser = userService.getByEmail(user.getEmail());
        if (dbUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        // Verify password
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Generate JWT token
        // Option 1: if JwtUtil accepts only email
        String token = jwtUtil.generateToken(dbUser.getEmail());

        // Option 2: if JwtUtil accepts id, email, role (update JwtUtil accordingly)
        // String token = jwtUtil.generateToken(dbUser.getId(), dbUser.getEmail(), dbUser.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", dbUser.getEmail());
        response.put("role", dbUser.getRole());

        return ResponseEntity.ok(response);
    }
}
