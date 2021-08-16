package xyz.micrqwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.micrqwe.listener.ProductRocketMQ;
import xyz.micrqwe.model.PageResultMapper;
import xyz.micrqwe.model.ResultMapper;
import xyz.micrqwe.model.User;
import xyz.micrqwe.service.HelloService;
import xyz.micrqwe.service.impl.MiaoshaServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by le on 2017/5/3.
 */
@RestController
@RefreshScope
@RequestMapping("/home/test")
public class HelloController {
    int i = 0;
    @Autowired
    HelloService helloService;
    @Autowired
    MiaoshaServiceImpl miaoshaService;
    @Autowired
    private ProductRocketMQ productRocketMQ;
    @Value("${testDevProperties}")
    private String dbUrl;
    @Value("${test.local:moren}")
    private String testApollo;
    @GetMapping("/getDbUrl")
    public String getDbUrl(HttpServletRequest request){
        return dbUrl+":"+testApollo;
    }

    @GetMapping("/productMq")
    public ResultMapper productMq() {
        productRocketMQ.convertAndSend();
        return new ResultMapper(200, System.currentTimeMillis()+"");
    }

    @GetMapping("/testLong")
    @ResponseBody
    public ResultMapper<PageResultMapper<Map>> testLong() {
        ResultMapper<PageResultMapper<Map>> map = new ResultMapper<PageResultMapper<Map>>();

        PageResultMapper<Map> map1 = new PageResultMapper<Map>();
        map1.setData(new HashMap());
        
        map.setData(map1);
        return map;
    }


    @GetMapping("/doInsert")
    @ResponseBody
    public String doInsert() {
        helloService.bingfa();
        return "333333";
    }

    @GetMapping("/doUpdate")
    @ResponseBody
    public String doUpdate() {
        helloService.update();
        return "333333";
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "333333";
    }

    @RequestMapping("/homeTest")
    @ResponseBody
    public User homeTest(HttpServletResponse response, Map map) {
        System.out.println(response);
        System.out.println(map);
        User user = new User();
        user.setAge("22");
        user.setName("登陆成功");
        user.setDate(new Date());
        return user;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        helloService.a();
        return "xxxxx";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(User user) {
        System.out.println(user.toString());
//        try {
//            Thread.sleep(1000*3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "测试";
    }

    @RequestMapping(value = "/thread", method = RequestMethod.GET)
    @ResponseBody
    public String thread() {
        System.out.println("来了请求了" + i);
        i++;
        try {
            Thread.sleep(1000 * 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "测试11111111";
    }

    //    @RequestMapping("/login")
////    @ResponseBody
//    public String login() {
//        return "login";
//    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return "login";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello登录成功";
    }

    @RequestMapping("/loginerror")
    @ResponseBody
    public String error() {
        return "登录失败";
    }


    @RequestMapping("/miao")
    @ResponseBody
    public Object miaosha() {
        Object o = miaoshaService.miaosha();
        return o;
    }
}
