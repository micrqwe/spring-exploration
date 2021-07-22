package xyz.micrqwe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import xyz.micrqwe.dao.CityMapper;
import xyz.micrqwe.service.HelloService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by le on 2017/5/5.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private DataSourceTransactionManager txManager;

    @Override
    public String hello() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("prdTypeCd", "tmbl101");
        map.put("startAnlsDateStr", "2017-01-01");
        map.put("endAnlsDateStr", "2017-04-01");
//        List brandVolumeDataList = cityMapper.findByState();
        System.out.println();
        return "这是junit测试";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void a() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 2);
        map.put("name", "xx");
//        cityMapper.insrtSwx(map);
        try {
            this.b();
        } catch (RuntimeException e) {
            System.out.println("发生事物的处理");
        }
        this.c();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void b() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 1);
        map.put("name", "xx");
//        cityMapper.insrtSwx(map);
        throw new RuntimeException("xx");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void c() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 3);
        map.put("name", "xx");
//        cityMapper.insrtSwx(map);
    }

    @Override
    @Transactional
    public String bingfa() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", "1");
        map.put("roleDesc", "xx");
        map.put("custId", 22);
//        cityMapper.insrtRole(map);
        return null;
    }

    @Override
    public String update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
                TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
                int i = 0;
                try {
                    System.out.println("开始进来了");
//                    i = cityMapper.updateRole(null);
                    System.out.println(i + "              ::::::::::::::::");
                    if (i == 0) {
                        throw new RuntimeException("重复当前了");
                    }
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("重复完成");
                    txManager.commit(status);
                } catch (Exception e) {
                    txManager.rollback(status);
                }
            }
        }).start();

        return "2222222222222222";
    }


}
