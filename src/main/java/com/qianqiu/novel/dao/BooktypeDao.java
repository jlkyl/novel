package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Booktype;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BooktypeDao {

    @Select("select * from booktype")
    public List<Booktype> queryAll();

    @Select("select * from booktype where typeid = #{typeid}")
    public Booktype queryById(Integer typeid);

    @Insert("INSERT INTO `novel`.`booktype` (`typeid`, `typename`, `icon`, `parentid`, `operateeid`, `operatedate`) VALUES (NULL, #{typename}, #{icon}, #{parentid}, #{operateeid},NOW())")
    public int add(Booktype bt);

    @Update("UPDATE `novel`.`booktype` SET `typeid`=#{typeid}, `typename`=#{typename}, `icon`=#{icon}, `parentid`=#{parentid}, `operateeid`=#{operateeid}, `operatedate`=NOW()' WHERE (`typeid`=#{typeid})")
    public int update(Booktype bt);

    @Delete("delete from booktype where typeid = #{typeid}")
    public int del(Integer typeid);
}
