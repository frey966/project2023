package com.example.exchange.tt2;

import com.example.atguigu.config.BuiltinExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@Slf4j
public class DelayedQueueConfig {
    //队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    //交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    //路由
    public static final String DELAYED_ROUNTING_KEY = "delayed.routingkey";

    //@Bean("delayedQueue")
    @Bean
    public Queue delayedQueue() {

        return new Queue(DELAYED_QUEUE_NAME);
    }


    //声明交换机 基于插件
    //@Bean("delayedExchange")
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", BuiltinExceptionType.Direct.getType());
        /**
         * 1.交换机的名字
         * 2.交换机的类型
         * 3.是否需要持久化
         * 4.是否自动删除
         * 5.参数
         */
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", false, false, arguments);
    }

    @Bean
    public Binding delayedQueueBindingDelayedExchange(@Qualifier("delayedQueue") Queue delayedQueue,@Qualifier("delayedExchange") CustomExchange delayedExchange)
    {

        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUNTING_KEY).noargs();
    }

}
