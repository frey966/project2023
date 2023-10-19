package com.example.shareSphere.mq5;



import com.example.shareSphere.config.RabbitUtils;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * 消费者
 * 接收队列一消息
 *
 * @author yt
 * @create 2022/10/14 13:39
 */
public class Consumer_Topic2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        //创建channel
        Channel channel = connection.createChannel();

        String queue2Name = "test_topic_queue2";

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
                System.out.println("body = " + new String(body));
                System.out.println("将日志信息保存至数据库");
            }
        };
        //接收队列一
        channel.basicConsume(queue2Name, true, consumer);


    }


}