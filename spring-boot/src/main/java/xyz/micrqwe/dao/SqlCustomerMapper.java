package xyz.micrqwe.dao;


import java.util.List;
import java.util.Map;

/**
 * Created by shaowenxing on 2018/4/20.
 */
//@Mapper
public interface SqlCustomerMapper {

    //    @Select("${sql}")  @Param("sql")
    public List<Map<String, Object>> query(String sql);
}
