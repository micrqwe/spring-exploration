package xyz.micrqwe.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.micrqwe.model.File;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fyt
 * @since 2021-09-08
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    File getByXml(@Param("id") int id);
}
