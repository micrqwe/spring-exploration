package xyz.micrqwe.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.micrqwe.listener.ProductRocketMQ;

/**
 * description TODO
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-07-16 14:12
 **/
public class RocketMQTest extends Common{
    @Autowired
    private ProductRocketMQ productRocketMQ;
    @Test
    public void runs(){
//        productRocketMQ.asyncSend();
    }
}
