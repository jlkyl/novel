package com.qianqiu.novel.dao;

import java.util.List;

import com.qianqiu.novel.entity.Menus;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IMenusDAO {
    @Select("<script>select * from menus" +
            "<if test=\"offset!=null and pageSize!=null\">\n" +
            "   limit #{offset},#{pageSize}\n" +
            "</if></script>")
    List<Menus> findAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    @Insert("insert into menus values (null,#{text},#{url},#{parentId},#{operateeid},now(),#{iconCls})")
    @SelectKey(keyColumn = "id",keyProperty = "id",before = false,resultType = Integer.class,statement = "select max(id) from menus")
    Integer add(Menus menu);
    @Update("update menus set text=#{text},url=#{url},parentId=#{parentId},operateeid=#{operateeid},operatedate=now(),iconCls=#{iconCls} where id=#{id}")
    Integer update(Menus menu);
    @Delete("delete from menus where id = #{id}")
    Integer del(Integer id);
    @Select("select count(*) from menus")
    Integer getCount();
    @Select("select * from menus where id in (select menuid from role_menu where roleid = #{roleid})")
    List<Menus> findByRid(Integer roleid);
}
