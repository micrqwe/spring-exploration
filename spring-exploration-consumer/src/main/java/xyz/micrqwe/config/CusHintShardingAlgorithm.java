package xyz.micrqwe.config;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * description 自定义路由
 *
 * @author shaowenxing
 * @version 1.0
 * @date 2021-12-01 15:34
 **/
public class CusHintShardingAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        return Arrays.asList("ds1");
    }
}
