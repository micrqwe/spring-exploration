package xyz.micrqwe.dubbo.nacos.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;
import xyz.micrqwe.dubbo.nacos.DemoService;

import java.util.concurrent.CompletableFuture;

@DubboService(version = "1.0.0")
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayName(String name) {
        return name;
    }

    @Override
    public String sayHello(String name) {
        return "Hello " + name + ", response from provider: "+ RpcContext.getServerContext().getObjectAttachments().toString();
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    }
}
