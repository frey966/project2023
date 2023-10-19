package com.example.exchange.dead;

import com.example.atguigu.config.BuiltinExceptionType;
import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeadConsummer01 {

    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        //声明死信队列和普通交换机  类型为direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExceptionType.Direct.getType());

        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT.getType());

        Map<String, Object> arguments = new HashMap<>();
        //过期时间 10秒 可以生产者设置
        //arguments.put("x-message-ttl","100000");
        //正常队列设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key", "lisi");
        //普通队列
        channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);
        //声明死信
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
        //普通交换机绑定普通队列
        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "zhangsan");
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");
        System.out.println("C1等待接收消息...........!");
        //接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("Consummer01:" + new String(message.getBody(), "UTF-8"));
        };
        //消费者取消消息是回调接口
        channel.basicConsume(NORMAL_QUEUE, true, deliverCallback, consumerTag -> {
        });
    }
}
