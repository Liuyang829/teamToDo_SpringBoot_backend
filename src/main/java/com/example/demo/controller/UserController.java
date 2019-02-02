package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/hello")
    public Result hello() {
        Subject subject = SecurityUtils.getSubject();

        //根据cookie获取信息
        User user = (User) subject.getPrincipal();
        String message= "hello "+user.getName();
        return ResultFactory.buildSuccessResult(message);
    }

    @RequestMapping("/toLogin")
    public Result toLogin() {
        return ResultFactory.buildnoAuthorizationResult(null);
    }

    @RequestMapping("/noAuth")
    public Result noAuth() {
        return ResultFactory.buildForbiddenResult(null);
    }


    @RequestMapping(method = RequestMethod.POST,value ="/login")
    public Result login(String email,String password) {
        Subject subject = SecurityUtils.getSubject();

        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        try {
            subject.login(token);
            return ResultFactory.buildSuccessResult(null);
        } catch (UnknownAccountException e) {
            // e.printStackTrace();
            return ResultFactory.buildFailResult("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            // e.printStackTrace();
            return ResultFactory.buildFailResult("密码错误");
        }
    }

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST,value ="/register")
    public Result register(String email,String password,String name){
        User user=userService.getByEmail(email);
        if(user!=null){
            return ResultFactory.buildFailResult("邮箱已被注册");
        }else {
            user=new User(email,password,name);
        }
        userService.register(user);
        return ResultFactory.buildSuccessResult(null);
    }
}
