package com.example.exchange.tt2;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 */
@Slf4j
@Component
public class DelayQueueConsumer {
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveD(Message message) throws Exception {
        String msg = new String(message.getBody());
        log.info("当前时间:{},收到延迟队里的消息{}", new Date().toString(), msg);
    }
}
