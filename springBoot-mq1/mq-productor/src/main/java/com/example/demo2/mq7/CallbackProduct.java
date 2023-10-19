package com.example.shareSphere.mq7;



import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 实现 RabbitTemplate.ConfirmCallback 接口 实现里面的回调方法
 * 将 rabbitTemplate 用init方法注入到 ConfirmCallback中即可使用  @PostConstruct注解是将rabbitTemplate注入
 * @author yt
 * @create 2022/12/29 15:09
 * 添加回调方法即可显示成功与失败
 */
@Component
public class CallbackProduct implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //注入
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     *
     * @param correlationData  保存了回调信息的id及相关信息（可以自己将发送的消息放到里面）
     * @param ack 交换机是否收到消息true=收到了，false=没收到
     * @param cause 失败原因，成功=null，失败会有失败原因，可以作为日志保存
     */
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) { // 消息投递到broker 的状态，true表示成功
            System.out.println("消息发送到Broker成功！");
            System.out.println("correlationData = " + correlationData);
            System.out.println("ack = " + ack);
            System.out.println("cause = " + cause);
        } else { // 发送异常
            System.out.println("发送失败");
            System.out.println("correlationData = " + correlationData);
            System.out.println("ack = " + ack);
            System.out.println("cause = " + cause);
        }
    }

}