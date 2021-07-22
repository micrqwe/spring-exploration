package xyz.micrqwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.micrqwe.config.XmlBaiduConfig;
import xyz.micrqwe.dao.XmlDao;

/**
 * Created by shaowenxing on 2018/4/20.
 * 自定义sql语句
 */
@RequestMapping(value = "/sql")
@RestController
public class SqlCustomerController {
    //    @Autowired
//    BaiduConfig baiduConfig;
    //    @Autowired
    XmlBaiduConfig xmlBaiduConfig;
    @Autowired
    private XmlDao xmlDao;
//    @Autowired
//    private HelloWorldHessonService helloWorldHessonServicel;

    @RequestMapping(value = "/thread", method = RequestMethod.GET)
    public Object query() {

        return "sadfasdfds";
    }


}
