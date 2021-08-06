package xyz.micrqwe;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@ImportResource(value = "classpath:conf/*.xml")
public class GatewayApplication {
    public static void main(String[] args) throws Exception {
//        ## 消费端
//        DefaultMQPushConsumer.setVipChannelEnabled(false)
//## 生产端
//        DefaultMQProducer.setVipChannelEnabled(false);
        SpringApplication.run(GatewayApplication.class, args);
    }
}