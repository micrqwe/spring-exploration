package xyz.micrqwe.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import xyz.micrqwe.model.mq.OrderPaidEvent;

/**
 * description TODO
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-07-16 14:05
 **/
@Slf4j
@Configuration
public class TestTopicOneRocketMQ {
    @Service
    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
    public class MyConsumer1 implements RocketMQListener<String> {
        public void onMessage(String message) {
            log.info("received my-consumer_test-topic-1 message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-1",selectorExpression="clientA", consumerGroup = "my-consumer_test-topic-3")
    public class MyConsumerTest3 implements RocketMQListener<String> {
        public void onMessage(String message) {
            log.info("received my-consumer_test-topic-3 message: {}", message);
        }
    }
    @Service
    @RocketMQMessageListener(topic = "test-topic-1",selectorExpression="clientB", consumerGroup = "my-consumer_test-topic-2")
    public class MyConsumerTest1 implements RocketMQListener<String> {
        public void onMessage(String message) {
            log.info("received my-consumer_test-topic-2 message: {}", message);
        }
    }

}
