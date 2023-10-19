package com.example.shareSphere.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主要是创建一个交换机，创建一个队列，将二者绑定上即可，这里只简单列出方法，后续可以在此基础上进行拓展
 * @author yt
 * @create 2022/10/14 16:44
 */
@Configuration
public class RabbitMQConfig {
    //交换机名称
    public static final String EXCANGE_NAME = "boot_topic_echange";
    //队列名称
    public static final String QUEUE_NAME = "boot_queue";

    //交换机
    @Bean("bootExchange")
    public Exchange bootExchange() {
        //创建通配符交换机
        return ExchangeBuilder.topicExchange(EXCANGE_NAME).durable(true).build();
    }

    //队列
    @Bean("bootQueue")
    public Queue bootQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    //绑定关系

    /**
     * 那个队列和那个交换机进行绑定.最后with的是routing Key  这里展示的是通配符的配置
     */
    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}