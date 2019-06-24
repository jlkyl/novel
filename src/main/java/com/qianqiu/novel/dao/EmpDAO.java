package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Emps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpDAO {
    @Select("select * from emps uname=#{uname},pwd=#{pwd}")
    public Emps loginB(@Param("uname" )String uname, @Param("pwd")String pwd);

    @Select("select * from emps")
    public List<Emps> queryEmp();
}