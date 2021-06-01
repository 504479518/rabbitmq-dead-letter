package com.caesar.rabbitmq.deadletter.demo.enums;

import lombok.Getter;

/**
 * 消息队列枚举配置
 *
 * @author Nicolas Caesar
 */
@Getter
public enum QueueEnum {

    /**
     * 业务A消息队列
     */
    QUEUE_BUSINESS_A_PACED("dead.letter.demo.simple.business.exchange", "dead.letter.demo.simple.business.queuea", "dead.letter.demo.simple.deadletter.queuea.routingkey"),

    /**
     * 业务B消息队列
     */
    QUEUE_BUSINESS_B_PACED("dead.letter.demo.simple.business.exchange", "dead.letter.demo.simple.business.queueb", "dead.letter.demo.simple.deadletter.queueb.routingkey"),

    /**
     * 死信A消息队列
     */
    QUEUE_TTL_A_PACED("dead.letter.demo.simple.deadletter.exchange", "dead.letter.demo.simple.deadletter.queuea", "dead.letter.demo.simple.deadletter.queuea.routingkey"),

    /**
     * 死信B消息队列
     */
    QUEUE_TTL_B_PACED("dead.letter.demo.simple.deadletter.exchange", "dead.letter.demo.simple.deadletter.queueb", "dead.letter.demo.simple.deadletter.queueb.routingkey"),

    /**
     * 订单消息通知队列
     */
    QUEUE_ORDER_PACED("erp.order.direct", "erp.order.paced", "erp.order.paced"),

    /**
     * 订单消息通知ttl队列
     */
    QUEUE_TTL_ORDER_PACED("erp.order.direct.ttl", "erp.order.paced.ttl", "erp.order.paced.ttl"),

    /**
     * 快递物流消息通知队列
     */
    QUEUE_LOGISTICS_EXPRESS_PACED("erp.logistics.express.direct", "erp.logistics.express.paced", "erp.logistics.express.paced"),

    /**
     * 快递物流消息通知ttl队列
     */
    QUEUE_TTL_LOGISTICS_EXPRESS_PACED("erp.logistics.express.direct.ttl", "erp.logistics.express.paced.ttl", "erp.logistics.express.paced.ttl");

    /**
     * 交换名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String name;

    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
