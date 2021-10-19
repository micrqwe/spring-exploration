package xyz.micrqwe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = {"xyz.micrqwe.dao"})
//@ImportResource(value = "classpath:conf/*.xml")
public class ExplorationApplication {
    public static void main(String[] args) throws Exception {
//        ## 消费端
//        DefaultMQPushConsumer.setVipChannelEnabled(false)
//## 生产端
//        DefaultMQProducer.setVipChannelEnabled(false);
        SpringApplication.run(ExplorationApplication.class, args);
    }
}