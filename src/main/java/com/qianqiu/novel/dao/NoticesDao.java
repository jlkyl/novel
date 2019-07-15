package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Notices;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticesDao {

    @Select(value = "select *  from notices order by opeartedate desc")
    List<Notices> query();
    @Insert(value = "INSERT INTO `notices` ( `title`, `content`, `operateeid`, `opeartedate`) " +
            " VALUES ( #{title}, #{content}, #{operateeid}, #{opeartedate})")
    int add(Notices n);
    @Update(value = "UPDATE `notices` SET `title`=#{title}, `content`= #{content}," +
            " `operateeid`=#{operateeid}, `opeartedate`=#{opeartedate} WHERE (`noticeid`=#{noticeid})")
    int update(Notices n);
    @Delete(value = "Delete from notices where noticeid =#{noticeid}")
    int del(Integer noticeid);
    @Select(value="select * from notices where noticeid =#{noticeid}")
    Notices queryByIds(Integer noticeid);
    @Select(" select *,getEmp(operateeid) empname from notices limit #{offset},#{pageSize}")
    List<Map<String,Object>> queryPage(@Param("offset") Integer offset , @Param("pageSize") Integer pageSize);
    @Select(value = "select count(noticeid) from notices ")
    Integer getCounts();
}
