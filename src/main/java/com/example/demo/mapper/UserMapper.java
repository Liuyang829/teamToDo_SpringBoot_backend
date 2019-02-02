package com.example.demo.mapper;

import com.example.demo.domain.User;

public interface UserMapper {
    public User getByEmail(String email);
    public void register(User user);
}
