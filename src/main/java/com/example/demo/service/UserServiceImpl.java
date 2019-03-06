package com.example.demo.service;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByEmail(String email){
        return userMapper.getByEmail(email);
    }

    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Override
    public String getVerifyCode(String email) {
        return userMapper.getVerifyCode(email);
    }

    @Override
    public void addVerifyCode(String email, String code) {
        userMapper.addVerifyCode(email,code);
    }

    @Override
    public void updateVerifyCode(String email, String code) {
        userMapper.updateVerifyCode(email,code);
    }
}
