package xyz.micrqwe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.annotation.QueryAnnotation;
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
