package com.example.confirm1;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 发布模式确认
 * 1.单个确认
 * 2，批量确认
 * 3.异步批量确认
 */
public class ConfirmMessage1 {
    //批量发送消息的个数
    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        publishMessageAsync();
    }
    /**
     * 3.处理异步批量确认消息
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
        /**
         *
         * 线程安全有序的一个哈希表，适用与高并发的情况下
         * 1.轻松的将序号与消息进行关联
         * 2.轻松批量删除条目 只要给到序号
         * 3.支持高并发(多线程)
         */
        ConcurrentSkipListMap<Long, String> outStandConfirms = new ConcurrentSkipListMap<>();
        //开启确认
        channel.confirmSelect();
        long beginTime = System.currentTimeMillis();
        //消息确认成功 回调函数
        ConfirmCallback ackCallBack = (deliveryTag, multiple) -> {
            if (multiple) {
                //批量的调用
                System.out.println("-------multiple-------");
                //2.删除掉已经确认的消息，剩下的就是未确认的消息
                ConcurrentNavigableMap<Long, String> confirmed = outStandConfirms.headMap(deliveryTag);
                confirmed.clear();
            }
            {
                //单个确认
                outStandConfirms.remove(deliveryTag);
            }
                System.out.println("确认消息:" + deliveryTag);
        };
        //消息确认失败 回调函数
        /**
         * 1.消息的标记
         * 2.是否为批量确认
         */
        ConfirmCallback nackCallBack = (deliveryTag, multiple) -> {
            //3. 打印一下未确认的消息
            String message = outStandConfirms.get(deliveryTag);
            System.out.println("未确认消息是"+message+"-----未确认消息标记:" + deliveryTag);
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
            //1.在此处记录下所有要发送的消息  消息的总和
            outStandConfirms.put(channel.getNextPublishSeqNo(), message);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时:" + (endTime - beginTime));
    }
}
