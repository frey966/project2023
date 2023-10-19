package com.example.exchange.direct;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.util.Scanner;
public class Proudce {
    public static final String EXCHANGENAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            //channel.basicPublish(EXCHANGENAME, "error", null, message.getBytes());
            channel.basicPublish(EXCHANGENAME, "info", null, message.getBytes());
            System.out.println("生产者发送消息:" + message);
        }
    }
}