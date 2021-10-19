package xyz.micrqwe.dubbo.nacos;

import java.util.concurrent.CompletableFuture;

public interface TestDemo1Service {

    String sayName(String name);


    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
