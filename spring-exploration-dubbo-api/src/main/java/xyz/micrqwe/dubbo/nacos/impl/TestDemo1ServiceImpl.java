package xyz.micrqwe.dubbo.nacos.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import xyz.micrqwe.dubbo.nacos.TestDemo1Service;

import java.util.concurrent.CompletableFuture;

@DubboService(version = "1.0.0")
@Service
public class TestDemo1ServiceImpl implements TestDemo1Service {

    @Override
    public String sayName(String name) {
        return name;
    }

    @Override
    public String sayHello(String name) {
        return "Hello " + name + ", response from provider: " ;
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    }
}
