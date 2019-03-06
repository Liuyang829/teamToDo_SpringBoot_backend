package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {
    public User getByEmail(String email);
    public void register(User user);
    String getVerifyCode(String email);
    void addVerifyCode(String email,String code);
    void updateVerifyCode(String email,String code);
}
