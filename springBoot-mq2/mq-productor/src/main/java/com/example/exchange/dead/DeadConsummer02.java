package com.example.exchange.dead;

import com.example.atguigu.config.BuiltinExceptionType;
import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * 接受死信队列的消息
 */
public class DeadConsummer02 {
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();

        System.out.println("C2等待接收消息...........!");
        //接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("Consummer02:" + new String(message.getBody(), "UTF-8"));
        };
        //消费者取消消息是回调接口
        channel.basicConsume(DEAD_QUEUE, true, deliverCallback, consumerTag -> {
        });
    }
}
