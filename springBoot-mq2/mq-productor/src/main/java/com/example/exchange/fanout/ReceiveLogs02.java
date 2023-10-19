package com.example.exchange.fanout;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者
 */
public class ReceiveLogs02 {

    public static final String EXCHANGENAME = "logs";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGENAME, "fanout");
        //声明一个临时队列
        /**
         * 生产一个临时队列，队列的名称是随机
         * 当消费者断开与队列的链接的时候，队列就自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGENAME, "34");
        System.out.println("c2等待接收消息，把接收到消息打印在屏幕上..............");
        //接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("C2控制台打印接收到消息:" + new String(message.getBody(), "UTF-8"));
        };
        //消费者取消消息是回调接口
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
