package com.example.confirm;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;

import java.util.UUID;

/**
 * 发布模式确认
 * 1.单个确认
 * 2，批量确认
 * 3.异步批量确认
 */
public class ConfirmMessage {
    //批量发送消息的个数
    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        // 1.单个确认 发布1000个单独确认消息,耗时:81473
        //publicMessageIndividuanlly();
        // 2.批量确认 发布1000个批量确认消息,耗时:72268
        // publicMessageIndividuanlly();
        // 3.异步批量确认  发布1000个单独确认消息,耗时:187
        publishMessageAsync();
    }

    /**
     * 1.单个确认
     *
     * @throws Exception
     */
    public static void publicMessageIndividuanlly() throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        String queunName = UUID.randomUUID().toString();
        /**
         * 1.是否持久化 true  yes
         * 2.是否独占  true  yes
         * 3.是否自动删除  true yes
         * 4.参数
         */
        channel.queueDeclare(queunName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queunName, null, message.getBytes());
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时:" + (endTime - beginTime));
    }


    /**
     * 2.批量确认
     *
     * @throws Exception
     */
    public static void publishMessageBatch() throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        String queunName = UUID.randomUUID().toString();
        channel.queueDeclare(queunName, true, false, false, null);
        channel.confirmSelect();
        long beginTime = System.currentTimeMillis();
        //批量确认消息大小
        int batchSize = 100;
        //批量发送，批量的确认
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queunName, null, message.getBytes());
            //判断达到100条消息的时候，批量确认一次
            if (i % batchSize == 0) {
                //发布确认
                channel.waitForConfirms();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时:" + (endTime - beginTime));
    }

    /**
     * 3.异步发布确认
     *
     * @throws Exception
     */
    public static void publishMessageAsync() throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        String queueNmae = UUID.randomUUID().toString();
        //队列声明
        channel.queueDeclare(queueNmae, true, false, false, null);
        //开启确认
        channel.confirmSelect();
        long beginTime = System.currentTimeMillis();
        //消息确认成功 回调函数
        ConfirmCallback ackCallBack = (deliveryTag, multiple) -> {
            System.out.println("---成功确认消息:" + deliveryTag);
        };
        //消息确认失败 回调函数
        /**
         * 1.消息的标记
         * 2.是否为批量确认
         */
        ConfirmCallback nackCallBack = (deliveryTag, multiple) -> {
            System.out.println("----------未确认消息:" + deliveryTag);
        };
        /**
         * 1.监听成功消息
         * 2.监听失败的消息
         */
        channel.addConfirmListener(ackCallBack, nackCallBack);
        //批量发送，批量的确认
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueNmae, null, message.getBytes());
            //判断达到100条消息的时候，批量确认一次
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时:" + (endTime - beginTime));
    }
}
