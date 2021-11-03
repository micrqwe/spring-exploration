package xyz.micrqwe.service.impl;

import xyz.micrqwe.dao.ShardingTestMapper;
import xyz.micrqwe.model.ShardingTest;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * (ShardingTest1)表服务实现类
 *
 * @author d
 * @since 2021-11-03 14:15:26
 */
@Service("xyz.micrqwe.entity.ShardingTest:Service")
public class ShardingTestServiceImpl extends ServiceImpl<ShardingTestMapper, ShardingTest> {

}
