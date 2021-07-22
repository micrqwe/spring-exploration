package xyz.micrqwe.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.micrqwe.model.ResultMapper;

/**
 * Created by le on 2017/5/9.
 */
@RestController
@RequestMapping(value = {"/login"})
public class LoginController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    /*@RequestMapping("/success")
    @ResponseBody
    public ObjectMapper success() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ObjectMapper("1", authentication.getPrincipal());
    }*/

    @RequestMapping("/success")
    @ResponseBody
    public ResultMapper success() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResultMapper(200, "登陆成功");
    }

    @RequestMapping("/error")
    @ResponseBody
    public ResultMapper error() {
        return new ResultMapper(1002, "用户名密码错误");
    }

    @RequestMapping("/no")
    @ResponseBody
    public ResultMapper no() {
        return new ResultMapper(1004,"用户未登陆");
    }

    @RequestMapping("/noLogin")
    @ResponseBody
    public ResultMapper noLogin() {
        return new ResultMapper(1002, "没有登陆");
    }

}
