package com.example.atguigu.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



/**
 * @author yt
 * @create 2022/10/14 14:30
 */
public class RabbitUtils2 {

    public static Channel getChannel() throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        //设置参数
        factory.setHost("18.166.225.206");
        //端口
        factory.setPort(5672);
        //自己在网页上创建的虚拟机
        factory.setVirtualHost("/cloud");
        //账号密码
        factory.setUsername("cloud");
        factory.setPassword("12345678");
        //创建连接
        Connection connection = factory.newConnection();
        //创建channel
        Channel channel = connection.createChannel();
        return  channel;
    }
}