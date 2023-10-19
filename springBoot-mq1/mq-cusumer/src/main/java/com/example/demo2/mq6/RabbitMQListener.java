package com.example.shareSphere.mq6;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yt
 * @create 2022/10/14 17:15
 */

/*
@Component
public class RabbitMQListener {
    @RabbitListener(queues = "boot_queue")
    public void ListenerQueue(Message message) {
        System.out.println("message = " + message);
        System.out.println("收到消息为：" + new String(message.getBody()));

    }
}
*/
