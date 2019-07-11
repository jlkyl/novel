package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Coverpush;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CoverpushDao {
    @Select(value = "select *  from coverpush c join books b on b.bookid = c.bookid")
    List<Coverpush> query();
    @Insert(value = "INSERT INTO `novel`.`coverpush` ( `bookid`, `cover`, `operateeid`, `operatedate`) " +
           " VALUES ( #{bookid}, #{cover}, #{operateeid}, #{operatedate})")
    int add(Coverpush c);
    @Update(value = "UPDATE `novel`.`coverpush` SET `bookid`=#{bookid}, `cover`=#{cover}," +
            " `operateeid`=#{operateeid}, `operatedate`=#{operatedate} WHERE (`pushid`=#{pushid})")
    int update(Coverpush c);
    @Delete(value = "Delete from coverpush where pushid =#{pushid}")
    int del(Integer pushid);
    @Select(value="select * from coverpush where  pushid =#{pushid}")
    Coverpush queryById(Integer pushid);
    @Select(" select *,getEmp(operateeid) empname from coverpush  c join books b on b.bookid = c.bookid limit #{offset},#{pageSize}")
    List<Coverpush> queryPage(@Param("offset") Integer offset ,@Param("pageSize") Integer pageSize);
    @Select(value = "select count(pushid) from coverpush ")
    Integer getCounts();
}
