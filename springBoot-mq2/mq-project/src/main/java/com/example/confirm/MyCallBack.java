package com.example.confirm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 回调接口
 */
@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //注入
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机确认回调方法
     * 1.发消息 交换机接收到了  回调
     * 1.1 correlationData 保存回调消息的ID以及相关信息
     * 1.2 交换机收到消息 ack=true
     * 1.3 cause null
     * 2.发消息 交换机接收失败 回调
     * 2.1 correlationData 保存回调消息的ID及相关信息
     * 2.2交换机收到消息  ack=false
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData.getId() != null ? correlationData.getId() : "";
        if (ack == true) {
            log.info("交换机已经收到了ID为:{}的消息", id);
        } else {
            log.info("交换机未收到了ID为:{}的消息由于原因:{}", id, cause);
        }
    }

    /**
     * 可以在当消息传递过程中不可达到目的地时消息返回给生产者
     * 只有不到可达目的的时候 才进行回退
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
/*    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息{},被交换机()退回,原因:{},路由key:{}", new String(message.getBody()), exchange, replyText, routingKey);
        //RabbitTemplate.ReturnsCallback.super.returnedMessage(message, replyCode, replyText, exchange, routingKey);
    }*/

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息{},被交换机()退回,原因:{},路由key:{}",
                new String(returnedMessage.getMessage().getBody()), returnedMessage.getExchange(), returnedMessage.getReplyText(), returnedMessage.getRoutingKey());
    }


}
