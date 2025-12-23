package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User getByEmail(String email);
    User registerUser(User user);
}
