package com.suraev.Listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
public class NotificationListener {

    private HashSet<String> messages;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getOrderMessage(String order) {
        System.out.println(order);
        log.info("get message from OrderService", order);
    }
}
