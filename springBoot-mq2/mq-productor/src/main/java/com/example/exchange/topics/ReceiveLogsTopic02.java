package com.example.exchange.topics;

import com.example.atguigu.config.BuiltinExceptionType;
import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者C1
 */
public class ReceiveLogsTopic02 {

    public static final String EXCHANGE_NAME = "TOPIC_LOGS";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExceptionType.TOPIC.getType());
        String queueName = "Q2";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "*.*.rabbit");
        channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");
        System.out.println("c2等待接收消息........");
        //DeliverCallback var2, CancelCallback
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody(), ("UTF-8")));
            System.out.println("c2接收队列:"+queueName+"绑定键："+message.getEnvelope().getRoutingKey());
        };
        //自动应答
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });


    }
}
