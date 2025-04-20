package com.suraev.Listener;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

@Slf4j
@Service
public class NotificationListener {

    private HashSet<String> withAutoAckMessages;
    private HashSet<String> withoutAutoAckMessages;

    public NotificationListener() {
        withAutoAckMessages= new HashSet<>();
        withoutAutoAckMessages = new HashSet<>();
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getOrderMessage(String order) {
        System.out.println(order);
        log.info("get message from OrderService", order);

    }

    @RabbitListener(queues = "${rabbit.queue_with_auto_ack.name}")
    public void receiveMessageWithAutoAckMessage(String message) {
        // сообщение автоматически удалится из очереди
        log.info("Queue with auto ack got", message);
        if(withAutoAckMessages.contains(message)) {
            withAutoAckMessages.remove(message);
        } else {
            withAutoAckMessages.add(message);
            System.out.println("Simulation EXCEPTION");
        }
    }

    @RabbitListener(queues ="${rabbit.queue_without_auto_ack.name}", ackMode = "MANUAL")
    public void receiveMessageWithoutAutoAckMessage(String message, Channel channel,
                                                    @Header (AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        // ackMode - Manual - оповещаем RabbitMQ о получении сообщения
        log.info("Queue without auto ack got", message);
        if(withoutAutoAckMessages.contains(message)) {
            withoutAutoAckMessages.remove(message);
            channel.basicAck(tag,false);
            // basicAck - оповещение RabbitMq о том, что сообщение получили
        } else {
            withoutAutoAckMessages.add(message);
            System.out.println("EXCEPTION simulation");
            channel.basicNack(tag,false,true);
            //basicNack - отменить одно или несколько сообщений. 3 параметр показывает,
            // нужно ли вернуть отменённые сообщения в очередь
        }
    }
}
