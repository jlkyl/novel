package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Notices;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface NoticesDao {
    @Select(value = "select *  from notices")
    List<Notices> query();
    @Insert(value = "INSERT INTO `notices` ( `title`, `content`, `operateeid`, `opeartedate`) " +
            " VALUES ( #{title}, #{content}, #{operateeid}, #{opeartedate})")
    int add(Notices n);
    @Update(value = "UPDATE `notices` SET `title`=#{title}, `content`= #{content}," +
            " `operateeid`=#{operateeid}, `opeartedate`=#{opeartedate} WHERE (`noticeid`=#{noticeid})")
    int update(Notices n);
    @Delete(value = "Delete from notices where noticeid =#{noticeid}")
    int del(Integer noticeid);
    @Select(value="select * from notices where  noticeid =#{noticeid}")
     Notices queryById(Integer noticeid);
    @Select(" select * from notices limit #{offset},#{pageSize}")
    List<Notices> queryPage(@Param("offset") Integer offset ,@Param("pageSize") Integer pageSize);
    @Select(value = "select count(noticeid) from notices ")
    Integer getCounts();
}
