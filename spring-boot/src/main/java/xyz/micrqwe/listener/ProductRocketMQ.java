package xyz.micrqwe.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import xyz.micrqwe.model.mq.OrderPaidEvent;

import java.math.BigDecimal;

/**
 * description TODO
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-07-16 14:02
 **/
@Service
@Slf4j
public class ProductRocketMQ {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void convertAndSend() {
        //send spring message
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("i`m no tag").build());
        rocketMQTemplate.send("test-topic-1:clientA", MessageBuilder.withPayload("i`m is ClientA").build());
        rocketMQTemplate.send("test-topic-1:clientB", MessageBuilder.withPayload("see you i`m clientB").build());
    }

    public void asyncSend() {
        //send messgae asynchronously
        rocketMQTemplate.asyncSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                log.info("async onSucess SendResult=:{}", var1);
            }

            @Override
            public void onException(Throwable var1) {
                log.info("async onException Throwable={}", var1);
            }

        });
    }

    public void syncSendOrderly() {
        //Send messages orderly
        rocketMQTemplate.syncSendOrderly("orderly_topic", MessageBuilder.withPayload("Hello, World").build(),"hashkey");
        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
    }
}
