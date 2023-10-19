package com.example.atguigu.demo3;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 * 尚硅谷 RabbitMQ之消息手动应答（结果成功）
 * 消息在手动应答不丢失数据，放回队列种重新消费。
 */
public class Task3 {
    public static final String task_queue_name="ack_queue";

    public static void main(String[] args)throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        boolean durable=true; //持久化
        channel.queueDeclare(task_queue_name,durable,false,false,null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",task_queue_name,null,message.getBytes("UTF-8"));
            //消息持久保存到磁盘种
            //channel.basicPublish("",task_queue_name, MessageProperties.PERSISTENT_BASIC,message.getBytes("UTF-8"));
            System.out.println("生产者发布信息:"+message);
        }
    }
}
