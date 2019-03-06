package com.example.demo.mapper;

import com.example.demo.domain.User;

public interface UserMapper {
    User getByEmail(String email);
    void register(User user);
    String getVerifyCode(String email);
    void addVerifyCode(String email,String code);
    void updateVerifyCode(String email,String code);
}
