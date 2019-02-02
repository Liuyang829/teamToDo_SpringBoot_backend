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
        return;
    }
}
