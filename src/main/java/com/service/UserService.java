package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public User getByEmail(String email);

    User getByEmail(String email);
    User registerUser(User user);
}
