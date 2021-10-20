package xyz.micrqwe;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDubbo(scanBasePackages="xyz.micrqwe.dubbo.nacos.impl") //开启
public class FileApplication {
    public static void main(String[] args) throws Exception {
//        Destroy method 'close' on bean with name 'nacosServiceRegistry' threw an exception: java.lang.NullPointerException
        SpringApplication.run(FileApplication.class, args);
    }
}