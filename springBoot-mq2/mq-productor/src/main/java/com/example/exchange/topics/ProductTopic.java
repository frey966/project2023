package com.example.exchange.topics;

import com.example.atguigu.config.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.util.HashMap;
import java.util.Map;

public class ProductTopic {
    public static final String EXCHANGE_NAME = "TOPIC_LOGS";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = RabbitUtils.getConnection();
        //创建channel 信道
        Channel channel = connection.createChannel();
        Map<String,String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("quick.orange.rabbit","");
        bindingKeyMap.put("lazy.orange.elephant","");
        bindingKeyMap.put("quick.orange.fox","");
        bindingKeyMap.put("lazy.brown.fox","");
        bindingKeyMap.put("lazy.pink.rabbit","");
        bindingKeyMap.put("quick.brown.fox","");
        bindingKeyMap.put("quick.orange.male.rabbit","");
        bindingKeyMap.put("lazy.orange.male.rabbit","");
        for (Map.Entry<String,String> bindingKeyMap1:bindingKeyMap.entrySet()){
               String routingKey = bindingKeyMap1.getKey();
               String message = bindingKeyMap1.getValue();
               channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
               System.out.println("生产者发现发出消息"+message);
        }
    }
}
