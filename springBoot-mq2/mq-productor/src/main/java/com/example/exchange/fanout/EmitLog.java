package com.example.exchange.fanout;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Scanner;

/**
 * 生产者
 */
public class EmitLog {

    public static final String EXCHANGENAME = "logs";

    public static void main(String[] args) throws Exception {

        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGENAME,"fanout");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message =scanner.next();
            channel.basicPublish(EXCHANGENAME,"",null,message.getBytes());
            System.out.println("生产者发送消息:"+message);
        }
    }
}
