package com.example.exchange.ttl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件类
 */
@Configuration
@Slf4j
public class TtlQueueConfig {

    //普通交换机
    public static final String X_EXCHANGE = "X";
    //死信交换机
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    //普通队列名称
    public static final String Queue_A = "QA";
    public static final String Queue_B = "QB";
    //死信队里名称
    public static final String DEAD_LETTER_QUEUE = "QD";
    //新的普通队列
    public static final String Queue_C = "QC";

    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    //声明普通队列TTL为10S
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> anguments = new HashMap<>();
        //设置死信交换机
        anguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        anguments.put("x-dead-letter-routing-key", "YD");
        //设置TTL过期时间 单位是ms
        anguments.put("x-message-ttl", 50000);
        return QueueBuilder.durable(Queue_A).withArguments(anguments).build();
        //return QueueBuilder.nonDurable(Queue_A).withArguments(anguments).build();
    }

    //声明普通队列TTL为40S
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> anguments = new HashMap<>();
        //设置死信交换机
        anguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        anguments.put("x-dead-letter-routing-key", "YD");
        //设置TTL过期时间 单位是ms
        anguments.put("x-message-ttl", 60000);
        return QueueBuilder.durable(Queue_B).withArguments(anguments).build();
        //return  QueueBuilder.nonDurable(Queue_B).withArguments(anguments).build();
    }


    @Bean("queueC")
    public Queue queueC() {
        log.info("-----72------");
        Map<String, Object> anguments = new HashMap<>();
        //设置死信交换机
        anguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        anguments.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(Queue_C).withArguments(anguments).build();
        //return  QueueBuilder.nonDurable(Queue_B).withArguments(anguments).build();
    }

    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding queueABindingX(@Qualifier("queueA")Queue queueA, @Qualifier("xExchange")DirectExchange xExchange)
    {
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    @Bean
    public Binding queueBBindingY(@Qualifier("queueB")Queue queueB, @Qualifier("xExchange")DirectExchange xExchange)
    {
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    //-------------新增---------------
    @Bean
    public Binding queueCBindingX(@Qualifier("queueC")Queue queueC, @Qualifier("xExchange")DirectExchange xExchange)
    {
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }
    @Bean
    public Binding queueDBindingD(@Qualifier("queueD")Queue queueD, @Qualifier("yExchange")DirectExchange yExchange)
    {
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }



}
