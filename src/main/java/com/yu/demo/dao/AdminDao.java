package com.yu.demo.dao;

import com.yu.demo.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    @Select("select * from admin where username=#{username}")
    Admin queryByName(String username);
}
