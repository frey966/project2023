package com.example.shareSphere.mq6;




import com.example.shareSphere.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yt
 * @create 2022/10/14 16:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        //convertAndSend参数分别是：交换机名称，routingkey，发送的消息
        String msg = "springboot 整合 rabbitmq";
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCANGE_NAME, "boot.haha", msg);
    }
}