package xyz.micrqwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import xyz.micrqwe.listener.ProductRocketMQ;
import xyz.micrqwe.model.ResultMapper;
import xyz.micrqwe.service.impl.ChargeServiceImpl;

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
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private ProductRocketMQ productRocketMQ;

    @GetMapping("/schCount")
    @ResponseBody
    public ResultMapper schCount() {
        long i = chargeService.schCount();
        return new ResultMapper(200, i+"");
    }

    @GetMapping("/productMq")
    public ResultMapper productMq() {
        productRocketMQ.convertAndSend();
        return new ResultMapper(200, System.currentTimeMillis()+"");
    }

    @GetMapping("/redis")
    @ResponseBody
    public ResultMapper redis() {
        redisTemplate.opsForValue().set("tests","ssss",60, TimeUnit.MINUTES);
        return new ResultMapper(200, redisTemplate.opsForValue().get("tests"));
    }

}
