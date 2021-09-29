package xyz.micrqwe.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import xyz.micrqwe.model.mq.OrderPaidEvent;

/**
 * description 测试mq
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-07-16 14:05
 **/
@Slf4j
@Configuration
public class OrderConsumerRocketMQ {
    @Service
    @RocketMQMessageListener(topic = "orderly_topic", consumerGroup = "my-consumer-orderly_topic-0")
    public class MyConsumer0 implements RocketMQListener<String> {
        public void onMessage(String message) {
            log.info("received syncSendOrderly message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer-test-topic-0")
    public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {
        public void onMessage(OrderPaidEvent orderPaidEvent) {
            log.info("received orderPaidEvent: {}", orderPaidEvent);
        }
    }

}
