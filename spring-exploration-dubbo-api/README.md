# 配置中心切换使用
1. nacos使用的是springcloud的版本的, springboot2.4版本以上还需要引入以下依赖,或者启动配置参数:spring.cloud.bootstrap.enabled=true
```text
     <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
```