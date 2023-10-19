package com.example.atguigu.demo2;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task01 {

    public static final String QUEUE_NAME="helloPEAK";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitUtils.getConnection();

        //创建channel 信道
        Channel channel = connection.createChannel();
        /**
         * 创建队列Queue
         * 参数：
         * 1.queue：队列名称
         * 2.durable：是否持久化，当mq重启之后是否还在
         * 3.exclusive：是否独占，只能有一个消费者监听这个队列    当connection关闭时是否=删除队列
         * 4.aotoDelete:是否自动删除，当没有接收端时是否自动删除 null
         * 5.arguments：参数
         */
        //如果没有hello_world的队列会自动创建，有就不会创建
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.nextLine();
            /**
             * 1.exchange:交换机名称。简单模式用默认的“”
             * 2.routingket：路由名称
             * 3.props：配置信息
             * 4.body：字节数组   发送的数据
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("发送消息完成:"+message);
        }
        channel.close();
        connection.close();
    }



}
