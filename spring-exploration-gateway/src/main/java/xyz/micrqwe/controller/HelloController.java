package xyz.micrqwe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.micrqwe.filter.ProxyLoggingFilter;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/home/test")
public class HelloController {
    @Value("${testDbUrl:nulls}")
    private String host;

    @GetMapping("/host")
    public String getHost() {
        log.trace("trace:11111111111111111111111111");
        log.debug("debug:22222222222222222222222222222222");
        log.info("info:33333333333333333333");
        log.warn("warn:444444444444444444444444444444");
        log.error("error:555555555555555555555555");
        return host;
    }
    @GetMapping("/get")
    public String getDump() {
        ProxyLoggingFilter proxyLoggingFilter =new ProxyLoggingFilter();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            integerList.add(i);
        }
        return proxyLoggingFilter.getOrder()+"  "+integerList.size();
    }

}
