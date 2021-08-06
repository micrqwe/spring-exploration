package xyz.micrqwe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
