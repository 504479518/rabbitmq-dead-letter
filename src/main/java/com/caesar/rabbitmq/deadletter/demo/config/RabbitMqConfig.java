package com.caesar.rabbitmq.deadletter.demo.config;

import com.caesar.rabbitmq.deadletter.demo.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicolas Caesar
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 声明业务Exchange
     */
    @Bean("businessExchange")
    public FanoutExchange businessExchange() {
        return new FanoutExchange(QueueEnum.QUEUE_BUSINESS_A_PACED.getExchange());
    }

    /**
     * 声明死信Exchange
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(QueueEnum.QUEUE_TTL_A_PACED.getExchange());
    }

    /**
     * 声明业务队列A
     */
    @Bean("businessQueueA")
    public Queue businessQueueA() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", QueueEnum.QUEUE_TTL_A_PACED.getExchange());
//       x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", QueueEnum.QUEUE_TTL_A_PACED.getRouteKey());
        return QueueBuilder.durable(QueueEnum.QUEUE_BUSINESS_B_PACED.getName()).withArguments(args).build();
    }

    /**
     * 声明业务队列B
     */
    @Bean("businessQueueB")
    public Queue businessQueueB() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", QueueEnum.QUEUE_TTL_B_PACED.getExchange());
//       x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", QueueEnum.QUEUE_TTL_B_PACED.getRouteKey());
        return QueueBuilder.durable(QueueEnum.QUEUE_BUSINESS_B_PACED.getName()).withArguments(args).build();
    }


    /**
     * 声明死信队列A
     */
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA() {
        return new Queue(QueueEnum.QUEUE_TTL_A_PACED.getName());
    }

    /**
     * 声明死信队列B
     */
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB() {
        return new Queue(QueueEnum.QUEUE_TTL_B_PACED.getName());
    }

    /**
     * 声明业务队列A绑定关系
     */
    @Bean
    public Binding businessBindingA(@Qualifier("businessQueueA") Queue queue,
                                    @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 声明业务队列B绑定关系
     */
    @Bean
    public Binding businessBindingB(@Qualifier("businessQueueB") Queue queue,
                                    @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 声明死信队列A绑定关系
     */
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue,
                                      @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QueueEnum.QUEUE_TTL_B_PACED.getRouteKey());
    }

    /**
     * 声明死信队列B绑定关系
     */
    @Bean
    public Binding deadLetterBindingB(@Qualifier("deadLetterQueueB") Queue queue,
                                      @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QueueEnum.QUEUE_TTL_B_PACED.getRouteKey());
    }
}
