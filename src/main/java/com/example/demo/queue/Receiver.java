package com.example.demo.queue;

import com.example.demo.mail.MailModule;
import com.example.demo.service.UserService;
import com.example.demo.utils.GetRandomString;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@RabbitListener(queues = "Queue")//这里写的是监听的是哪个队列。
public class Receiver {
    @Autowired
    private UserService userService;

    @RabbitHandler
    public void process(String email) {
        System.out.println("接受端  : " + email);
        String code=userService.getVerifyCode(email);
        if(code==null){
            code= GetRandomString.get(6);
            userService.addVerifyCode(email,code);
        }else{
            code=GetRandomString.get(6);
            userService.updateVerifyCode(email,code);
        }

        try{
            MailModule.sendmail(email,code);
        }catch (Exception e){
            e.printStackTrace();
        }    }
}
