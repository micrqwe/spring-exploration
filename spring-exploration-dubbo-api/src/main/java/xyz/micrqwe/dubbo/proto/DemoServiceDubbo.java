    package xyz.micrqwe.dubbo.proto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@javax.annotation.Generated(
value = "by Dubbo generator",
comments = "Source: DemoService.proto")
public final class DemoServiceDubbo {
private static final AtomicBoolean registered = new AtomicBoolean();

public static boolean init() {
    if (registered.compareAndSet(false, true)) {
            org.apache.dubbo.common.serialize.protobuf.support.ProtobufUtils.marshaller(
            xyz.micrqwe.dubbo.proto.HelloReply.getDefaultInstance());
            org.apache.dubbo.common.serialize.protobuf.support.ProtobufUtils.marshaller(
            xyz.micrqwe.dubbo.proto.HelloRequest.getDefaultInstance());
    }
    return true;
}

private DemoServiceDubbo() {}

}
