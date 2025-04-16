package com.suraev.Listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationListener {

    @RabbitListener(queues = "rabbitmq.queque")
    public void getOrderMessage(String order) {
        log.info("get message from OrderService", order);
    }
}
