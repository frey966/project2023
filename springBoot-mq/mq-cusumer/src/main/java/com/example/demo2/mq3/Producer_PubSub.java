package com.example.shareSphere.mq3;

import com.example.shareSphere.config.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 * 生产者将消息发送给交换机，交换机将消息分散到队列中，然后消费者在对应的队列中消费
 *
 * 创建交换机
 * 创建两个队列
 * 将两个队列绑定至交换机上
 * 发送消息到交换机上
 * 关闭资源
 *
 * @author yt
 * @create 2022/10/14 13:40
 */
public class Producer_PubSub {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = RabbitUtils.getConnection();

        //创建channel 信道
        Channel channel = connection.createChannel();
        //创建交换机
        /**
         * String exchange,交换机名称
         * BuiltinExchangeType type, 交换机类型 @See BuiltinExchangeType 定向 广播 通配符 参数匹配
         * boolean durable, 是否持久化
         * boolean autoDelete, 是否自动删除
         * internal 内部使用 一般是false
         * Map<String, Object> arguments 参数列表
         */
        String ExchangeName = "test_fanout";
        channel.exchangeDeclare(ExchangeName, BuiltinExchangeType.FANOUT,true,false,false,null);
        //创建队列
        String queue1Name = "test_fanout_queue1";
        String queue2Name = "test_fanout_queue2";
        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);
        //绑定队列和交换机
        /**
         * String queue, 队列名称
         * String exchange, 交换机名称
         * String routingKey 路由key
         */
        channel.queueBind(queue1Name,ExchangeName,"");
        channel.queueBind(queue2Name,ExchangeName,"");
        //发送消息
        String body = "日志信息：张三调用了findAll方法  日志级别为：info";
        channel.basicPublish(ExchangeName,"",null,body.getBytes());
        //释放资源
        RabbitUtils.closeConnectionAndchannel(channel,connection);
    }
}