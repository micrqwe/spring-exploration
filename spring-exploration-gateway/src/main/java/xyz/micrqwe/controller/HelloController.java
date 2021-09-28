package xyz.micrqwe.controller;

import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import xyz.micrqwe.filter.ProxyLoggingFilter;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/home/test")
@RefreshScope
public class HelloController {
    @Value("${testDevProperties:nulls}")
    private String host;
    @GetMapping("/host")
    public String getHost() {
        log.trace("trace:11111111111111111111111111");
        log.debug("debug:22222222222222222222222222222222");
        log.info("info:33333333333333333333");
        log.warn("warn:444444444444444444444444444444");
        log.error("error:555555555555555555555555");
        return "test:"+host+";";
    }


}
