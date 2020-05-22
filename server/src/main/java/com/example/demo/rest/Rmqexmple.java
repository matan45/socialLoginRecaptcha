package com.example.demo.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class Rmqexmple {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void test(){
        rabbitTemplate.convertAndSend(Rmq.queueName, "Hello, world!");
    }

    @RabbitListener(queues = Rmq.queueName)
    public void listen(String in) {
       log.info(in);
    }
}
