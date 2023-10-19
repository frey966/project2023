package com.example.project9;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producter1 {

    public static final String QUEUE_NAME = "hello";

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
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", 10);//官方是0-255 目前是 0-10 设置过大浪费CPU和内存
        channel.queueDeclare(QUEUE_NAME, true, false, false, arguments);
        //发送消息
        for (int i = 0; i < 11; i++) {
         String message = "info"+i;
         if(i==5){
             System.out.println("----5---");
             AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();
             channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
         }
         else {
             channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
         }
        }
        System.out.println("----消息发送完---");
        /**
         * 1.exchange:交换机名称。简单模式用默认的“”
         * 2.routingket：路由名称
         * 3.props：配置信息
         * 4.body：字节数组   发送的数据
         */
/*        String body = "helloPEAK，我是于涛";
        channel.basicPublish("", QUEUE_NAME, null, body.getBytes());
        channel.close();
        connection.close();*/
    }
}
