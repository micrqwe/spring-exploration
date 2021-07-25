package xyz.micrqwe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home/test")
public class HelloController {
    @Value("${testDbUrl:nulls}")
    private String host;

    @GetMapping("/host")
    public String getHost() {
        return host;
    }
}
