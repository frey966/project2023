package com.example.shareSphere.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yt
 * @create 2022/10/14 14:30
 */
public class RabbitUtils {
    public static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        //设置参数
        connectionFactory.setHost("18.166.225.206");
        //端口
        connectionFactory.setPort(5672);
        //自己在网页上创建的虚拟机
        connectionFactory.setVirtualHost("cloud");
        //账号密码
        connectionFactory.setUsername("cloud");
        connectionFactory.setPassword("12345678");
    }

    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndchannel(Channel channel,Connection connection){
        try {
            if (channel!=null){
                channel.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}