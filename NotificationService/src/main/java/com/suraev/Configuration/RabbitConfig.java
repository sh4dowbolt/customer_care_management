package com.suraev.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.queue_with_auto_ack.name}")
    private String queueWithAutoAck;
    @Value("${rabbit.queue_without_auto_ack.name}")
    private String getQueueWithoutAck;

    @Bean
    public Queue  autoAckWithException() {
        return new Queue(queueWithAutoAck,false);
    }

    @Bean
    public Queue autoWithoutException() {
        return QueueBuilder.nonDurable(getQueueWithoutAck)
                .withArgument("x-dead-letter-exchange","")
                .withArgument("x-dead-letter-routing-key", getQueueWithoutAck)
                .build();
    }


}
