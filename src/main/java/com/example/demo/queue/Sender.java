package com.example.demo.queue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * 发送消息的方法
     */
    public void send(String msg) {
        this.amqpTemplate.convertAndSend("Queue",msg);
    }
}
