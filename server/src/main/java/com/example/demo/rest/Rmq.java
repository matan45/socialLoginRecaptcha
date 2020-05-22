package com.example.demo.rest;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Rmq {
   public static final String queueName = "test";
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }
}
