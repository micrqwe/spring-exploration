package xyz.micrqwe.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChargeMapper {

    @Insert("insert into sch_count(schId,counts) values(#{schId},#{counts})")
    int insrtSwx(Long schId,int counts);
    @Select("select max(schId) from sch_count")
    Long queryMaxSchId();
}