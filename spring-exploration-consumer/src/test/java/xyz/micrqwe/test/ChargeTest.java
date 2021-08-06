package xyz.micrqwe.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import xyz.micrqwe.controller.ChargeController;
import xyz.micrqwe.service.impl.ChargeServiceImpl;


//@SpringBootTest
public class ChargeTest extends Common {
    @Autowired
    private ChargeController chargeController;
    @Autowired
    private ChargeServiceImpl chargeService;
    @Test
    @Rollback
    @Transactional
    public void schCountController() throws Exception {
        Object o = chargeController.schCount();
        System.out.println(toJson(o));
    }
    @Test
    public void testExample() throws Exception {
        Object o = chargeService.schCount();
        System.out.println(toJson(o));
    }
}