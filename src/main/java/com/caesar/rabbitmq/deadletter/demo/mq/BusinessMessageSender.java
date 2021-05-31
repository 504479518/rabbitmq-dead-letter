package com.caesar.rabbitmq.deadletter.demo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.caesar.rabbitmq.deadletter.demo.config.RabbitMqConfig.BUSINESS_EXCHANGE_NAME;

/**
 * @author Nicolas Caesar
 */
@Component
public class BusinessMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg){
        rabbitTemplate.convertSendAndReceive(BUSINESS_EXCHANGE_NAME, "", msg);
    }
}
