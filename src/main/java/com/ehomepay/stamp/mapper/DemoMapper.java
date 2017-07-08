package com.ehomepay.stamp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DemoMapper {
    
    @Select("select 1 from dual")
    int queryCount();
    
}
