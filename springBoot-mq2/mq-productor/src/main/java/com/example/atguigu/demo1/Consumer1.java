package com.example.atguigu.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
    public static final String QUEUE_NAME = "helloPEAK";

    public static void main(String[] args) throws IOException, TimeoutException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置参数
        factory.setHost("18.166.225.206");
        //端口
        factory.setPort(5672);
        //自己在网页上创建的虚拟机
        factory.setVirtualHost("cloud");
        //账号密码
        factory.setUsername("cloud");
        factory.setPassword("12345678");

        //创建连接
        Connection connection = factory.newConnection();
        //创建channel
        Channel channel = connection.createChannel();

        //声明接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println("message="+new String(message.getBody()));
        };
        //取消消息是的回调
        CancelCallback cancelCallback = consumerTag->{
          System.out.println("消费消息被中断");
        };
        /**
         * 1.消费队列的名称
         * 2.消费成功之后是否要自动应答true, false 代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         *
         */

        channel.basicConsume(QUEUE_NAME, true, deliverCallback,cancelCallback);


    }
}
