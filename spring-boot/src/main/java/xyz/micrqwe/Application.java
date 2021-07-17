package xyz.micrqwe;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@MapperScan(basePackages = {"xyz.micrqwe.dao"})
//@ImportResource(value = "classpath:conf/*.xml")
public class Application {
    public static void main(String[] args) throws Exception {
//        ## 消费端
//        DefaultMQPushConsumer.setVipChannelEnabled(false)
//## 生产端
//        DefaultMQProducer.setVipChannelEnabled(false);
        SpringApplication.run(Application.class, args);
    }
}