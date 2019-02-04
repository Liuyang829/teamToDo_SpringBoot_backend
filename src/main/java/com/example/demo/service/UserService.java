package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {
    public User getByEmail(String email);
    public void register(User user);

}
