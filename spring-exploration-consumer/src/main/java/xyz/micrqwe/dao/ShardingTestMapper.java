package xyz.micrqwe.dao;

import xyz.micrqwe.model.ShardingTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Service;

/**
 * (ShardingTest1)表数据库访问层
 *
 * @author d
 * @since 2021-11-03 14:15:27
 */
@Service("xyz.micrqwe.entity.ShardingTest")
public interface ShardingTestMapper extends BaseMapper<ShardingTest> {
}

