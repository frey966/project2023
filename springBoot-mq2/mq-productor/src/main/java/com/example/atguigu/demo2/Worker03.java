package com.example.atguigu.demo2;

import com.example.atguigu.config.RabbitUtils2;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Worker03 {

    public static final String QUEUE_NAME="helloPeak";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitUtils2.getChannel();

        DeliverCallback deliverCallback =(consumerTag,message)->{
          System.out.println("接收到的消息meeage="+String.valueOf(message.getBody()));
        };

        CancelCallback cancelCallback =(consummerTag)->{
          System.out.println("消息者取消消费接口回调逻辑"+consummerTag);
        };
        System.out.println("C3等待接收消息------------");
        /**
         * 1.消费队列的名称
         * 2.消费成功之后是否要自动应答true, false 代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
