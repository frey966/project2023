package com.example.shareSphere.mq2;


import com.example.shareSphere.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 * 工作队列发送消息，一个发送者发送消息   n个消费者监听  只能有一个消费者消费
 *
 * @author yt
 * @create 2022/10/14 13:40
 */
public class Producer_WorkQueues {
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
        //发送消息
        /**
         * 1.exchange:交换机名称。简单模式用默认的“”
         * 2.routingket：路由名称
         * 3.props：配置信息
         * 4.body：字节数组   发送的数据
         */

        for (int i = 0; i < 10; i++) {
            String body = i + "你好呀，我是cloud-------";
            channel.basicPublish("", "work_queues", null, body.getBytes());
        }
        channel.close();
        connection.close();
    }
}