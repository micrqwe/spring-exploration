package xyz.micrqwe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by le on 2017/5/3.
 */
@RestController
@RefreshScope
@RequestMapping("/home/test")
public class HelloController {
    @Value("${test.apollo.properties:moren}")
    private String testApollo;

    @GetMapping("/getDbUrl")
    public String getDbUrl() {
        return testApollo;
    }
}
