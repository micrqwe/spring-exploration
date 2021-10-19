    package xyz.micrqwe.dubbo.proto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@javax.annotation.Generated(
value = "by Dubbo generator",
comments = "Source: DemoService.proto")
public interface DemoService {
static final String JAVA_SERVICE_NAME = "xyz.micrqwe.dubbo.proto.DemoService";
static final String SERVICE_NAME = "demoservice.DemoService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = DemoServiceDubbo.init();

    xyz.micrqwe.dubbo.proto.HelloReply sayHello(xyz.micrqwe.dubbo.proto.HelloRequest request);

    CompletableFuture<xyz.micrqwe.dubbo.proto.HelloReply> sayHelloAsync(xyz.micrqwe.dubbo.proto.HelloRequest request);


}
