package com.example.atguigu.demo4;

import com.example.atguigu.config.RabbitUtils;
import com.example.atguigu.config.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

public class Demo4Worker1 {

    public static final String task_queue_name = "ack_queue";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        System.out.println("C1等待接收消息---短时间处理---------");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            SleepUtils.sleep(5);
            System.out.println("--C3接收到的消息:" + new String(message.getBody(), "UTF-8"));
            //手动应答
            /**
             * 1.消息的标记 tag
             * 2.是否批量应答 false:不批量应答信道的消息 true:批量
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = (consummerTag) -> {
            System.out.println("消息者取消消费接口回调逻辑" + consummerTag);
        };
        //设置不公平分发
        int prefetchCount=1;
        channel.basicQos(prefetchCount);

        /**
         * 1.消费队列的名称
         * 2.消费成功之后是否要自动应答true, false 代表手动应答
         * 3.消费者成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(task_queue_name, false, deliverCallback, cancelCallback);
    }
}
