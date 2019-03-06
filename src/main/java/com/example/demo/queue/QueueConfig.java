package com.example.demo.queue;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
@Configuration
public class QueueConfig {

   
    @Bean
    public Queue createQueue(){
        return new Queue("Queue");
    }

}

