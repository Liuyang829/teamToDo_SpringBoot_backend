package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.context.annotation.Bean;

public interface UserService {
    public User getByEmail(String email);
    public void register(User user);

}
