package com.caesar.rabbitmq.deadletter.demo.controller;

import com.caesar.rabbitmq.deadletter.demo.mq.BusinessMessageSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicolas Caesar
 */
@RequestMapping("rabbitmq")
@RestController
public class RabbitMqMsgController {

    private final BusinessMessageSender sender;

    public RabbitMqMsgController(BusinessMessageSender sender) {
        this.sender = sender;
    }

    @RequestMapping("sendmsg")
    public void sendMsg(String msg){
        sender.sendMsg(msg);
    }
}
