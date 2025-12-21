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

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User dbUser = userService.getByEmail(loginRequest.getEmail());
        if (dbUser != null && dbUser.getPassword().equals(loginRequest.getPassword())) {
            // Generate JWT token
            return jwtUtil.generateToken(dbUser.getId(), dbUser.getEmail(), dbUser.getRole());
        }
        return "Invalid credentials";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
