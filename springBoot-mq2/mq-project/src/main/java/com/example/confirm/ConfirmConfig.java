package com.example.confirm;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布确认高级
 */
@Configuration
public class ConfirmConfig {

    public static final String CONFIRM_EXCHANGE_NAME="confirm_exchange";
    public static final String CONFIRM_QUEUE_NAME="confirm_queue";
    public static final String CONFIRM_ROUNTING_KEY="key1";
    public static final String BACKUP_EXCHANGE_NAME="backup_exchange";
    public static final String BACKUP_QUEUE_NAME="backup_queue";
    public static final String WARNING_QUEUE_NAME="warning_queue";

   /*
   @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
       return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }
    */

    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return  ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).withArgument("alternate-exchange",BACKUP_EXCHANGE_NAME).build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmQueue")Queue confirmQueue,@Qualifier("confirmExchange") DirectExchange confirmExchange){

        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUNTING_KEY);
    }

    //--------------备份交换机---------------------------------------------------------------------
    @Bean("backupExchange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    @Bean("backupQueue")
    public Queue backupQueue(){
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }
    @Bean("warningQueue")
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding backupQueueBindingExchange(@Qualifier("backupQueue")Queue backupQueue,@Qualifier("backupExchange") FanoutExchange backupExchange){

        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    @Bean
    public Binding warningQueueBindingExchange(@Qualifier("warningQueue")Queue warningQueue,@Qualifier("backupExchange") FanoutExchange backupExchange){

        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }

}
