package xyz.micrqwe.config;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.springframework.context.annotation.Bean;
import xyz.micrqwe.dubbo.nacos.DemoService;

public class ReferenceConfiguration {
    @Bean
    @DubboReference(group = "demo")
    public ReferenceBean<DemoService> helloService() {
        return new ReferenceBean();
    }

//    @Bean
//    @DubboReference(group = "demo", interfaceClass = HelloService.class)
//    public ReferenceBean<GenericService> genericHelloService() {
//        return new ReferenceBean();
//    }
}