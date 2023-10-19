package com.example.shareSphere.mq2;

import com.example.shareSphere.config.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author yt
 * @create 2022/10/14 13:39
 */
public class Consumer_WorkQueues1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
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
        channel.queueDeclare("work_queues", true, false, false, null);

        //接收消息
        /**
         * 1.queue:队列名称
         * 2.aotoACK：是否自动确认
         * 3.callback：回调对象
         */
        Consumer consumer = new DefaultConsumer(channel) {
            /**
             * 回调方法。当收到消息之后会自动执行该方法
             * @param consumerTag 标识
             * @param envelope 获取一些信息，交换机，路由Key
             * @param properties 配置信息
             * @param body 数据
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
   /*             System.out.println("consumerTag = " + consumerTag);
                System.out.println("Exchange = " + envelope.getExchange());
                System.out.println("RoutingKey = " + envelope.getRoutingKey());
                System.out.println("properties = " + properties);*/
                System.out.println("body = " + new String(body));
            }
        };
        channel.basicConsume("work_queues", true, consumer);


    }


}