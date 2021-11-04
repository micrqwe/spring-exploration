package xyz.micrqwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.micrqwe.listener.ProductRocketMQ;
import xyz.micrqwe.model.ResultMapper;
import xyz.micrqwe.service.impl.ChargeServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by le on 2017/5/3.
 */
@RestController
@RequestMapping("/home/test")
public class ChargeController {
    @Autowired
    ChargeServiceImpl chargeService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ProductRocketMQ productRocketMQ;


    @PostMapping("/test")
    @ResponseBody
    @CrossOrigin
    public ResultMapper test(HttpServletResponse response) {
        return new ResultMapper(200, 1 + ":2323");
    }

    @GetMapping("/schCount")
    @ResponseBody
    public ResultMapper schCount(HttpServletResponse response) {
        response.setHeader("ss", "dddd");
        long i = chargeService.schCount();
        return new ResultMapper(200, i + "");
    }

    @PostMapping("/response")
    public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        // TODO 跨域不能在controller里面写,filter已经被拦截掉了
        responseHeaders.set("Access-Control-Allow-Origin", "*");
//        responseHeaders.set("Access-Control-Allow-Headers", "content-type");
//        responseHeaders.set("Access-Control-Allow-Methods", "POST, OPTIONS");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Response with header using ResponseEntity");
    }

    @GetMapping("/productMq")
    public ResultMapper productMq() {
        productRocketMQ.convertAndSend();
        return new ResultMapper(200, System.currentTimeMillis() + "");
    }

    @GetMapping("/dubbo-nacos")
    public ResultMapper dubboNacos() {
        String test = chargeService.dubboNacos();


        return new ResultMapper(200, test);
    }

    @GetMapping("/redis")
    @ResponseBody
    public ResultMapper redis() {
        redisTemplate.opsForValue().set("tests", "ssss", 60, TimeUnit.MINUTES);
        return new ResultMapper(200, redisTemplate.opsForValue().get("tests"));
    }

}
