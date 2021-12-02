package xyz.micrqwe.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.micrqwe.listener.ProductRocketMQ;
import xyz.micrqwe.model.SchCount;
import xyz.micrqwe.model.ShardingTest;
import xyz.micrqwe.service.impl.SchCountServiceImpl;
import xyz.micrqwe.service.impl.ShardingTestServiceImpl;

import java.util.List;

/**
 * description TODO
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-07-16 14:12
 **/
public class RocketMQTest extends Common {
    @Autowired
    private ProductRocketMQ productRocketMQ;
    @Autowired
    private ShardingTestServiceImpl shardingTestService;
    @Autowired
    private SchCountServiceImpl schCountService;

    @Test
    public void runs() {
//        productRocketMQ.asyncSend();
    }

    @Test
    public void insert() {
        for (int i = 1; i < 3; i++) {
            ShardingTest shardingTest = new ShardingTest();
            shardingTest.setAge(i);
            shardingTest.setName("test" + i);
            shardingTestService.getBaseMapper().insert(shardingTest);
        }
    }

    @Test
    public void query() {
        for (int i = 1; i < 3; i++) {
            ShardingTest shardingTest = new ShardingTest();
            shardingTest.setAge(i);
            QueryWrapper<ShardingTest> queryWrapper = new QueryWrapper<>();
            queryWrapper.setEntity(shardingTest);
            List<ShardingTest> shardingTestList = shardingTestService.getBaseMapper().selectList(queryWrapper);
            toJson(shardingTestList);
        }
     /*   try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.setMasterRouteOnly();
            QueryWrapper<ShardingTest> queryWrapper = new QueryWrapper<>();
            queryWrapper.last(" order by id asc limit 5");
            List<ShardingTest> shardingTestList = shardingTestService.getBaseMapper().selectList(queryWrapper);
            toJson(shardingTestList);
        }*/

    }

    @Test
    public void queryPutong() {
/*        ShardingTest shardingTest = new ShardingTest();
        shardingTest.setAge(1);
        QueryWrapper<ShardingTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(shardingTest);
        List<ShardingTest> t1 = shardingTestService.getBaseMapper().selectList(queryWrapper);
        toJson(t1);*/
/*
        for (int i = 0; i < 10; i++) {
            List<SchCount> shardingTestList = schCountService.getBaseMapper().query();
            toJson(shardingTestList);
        }
*/

        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        for (int i = 0; i < 10; i++) {
            List<SchCount> te = schCountService.getBaseMapper().sharding();
            toJson(te);
        }
       /* for (int i = 0; i < 10; i++) {
            SchCount schCount= new SchCount();
            schCount.setCounts(1);
            schCount.setSchid(1);
            schCount.setMemo("tableMaster:"+i);
            schCountService.getBaseMapper().insert(schCount);
        }*/
    }
}
