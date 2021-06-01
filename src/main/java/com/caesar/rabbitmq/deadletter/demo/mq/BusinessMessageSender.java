package com.caesar.rabbitmq.deadletter.demo.mq;

import com.caesar.rabbitmq.deadletter.demo.enums.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nicolas Caesar
 */
@Component
public class BusinessMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.convertSendAndReceive(QueueEnum.QUEUE_BUSINESS_A_PACED.getExchange(), "", msg);
    }
}
