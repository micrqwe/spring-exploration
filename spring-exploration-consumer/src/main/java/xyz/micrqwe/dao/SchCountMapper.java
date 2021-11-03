package xyz.micrqwe.dao;

import xyz.micrqwe.model.SchCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * (SchCount)表数据库访问层
 *
 * @author d
 * @since 2021-11-03 17:27:10
 */
@Service("xyz.micrqwe.entity.SchCount")
public interface SchCountMapper extends BaseMapper<SchCount> {

    public List<SchCount> query();
}

