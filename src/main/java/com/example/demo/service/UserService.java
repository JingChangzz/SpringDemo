package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public User getUserByUsername(String username);
    public void delUserByUsername(String username);
}
