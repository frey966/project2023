package com.example.exchange.tt2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 通过插件产生
 * 生产者
 */
@RestController
@RequestMapping("tt2")
@Slf4j
public class DelayMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //开始发送消息基于插件的消息及延迟消息
    @GetMapping("sendDelayMsg/{message}/{delayTime}")
    public void sendDelayMsg(@PathVariable String message, @PathVariable Integer delayTime) {
        log.info("当前时间:{},发送一条时长{}毫秒信息给延迟队列delayed.queue:{}", new Date().toString(), delayTime, message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUNTING_KEY, message, msg -> {
            //发送消息的时候，延迟时长 单位：ms
            msg.getMessageProperties().setDelay(delayTime);
            return msg;
        });
    }
}
