package com.example.confirm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 确认消费者
 */
@Component
@Slf4j
public class ConfirmConsumer {
    @RabbitListener(queues =ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody());
        log.info("接受的队列confirm.queue消息:{}",msg);
    }
}
