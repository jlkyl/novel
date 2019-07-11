package com.qianqiu.novel.dao;

import java.util.List;

import com.qianqiu.novel.entity.Site;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ISiteDAO {
    @SelectKey(keyProperty = "siteid",keyColumn = "siteid",resultType = Integer.class,before = false,statement = "select max(siteid) from site")
    @Insert("insert into site values (null,#{sitename},#{siteurl},#{principal},#{phone},#{email},#{operateeid},now())")
    Integer add(Site site);
    @Select("select *,getEmp(operateeid) empname from site")
    List<Site> findAll();
    @Update("update site set sitename=#{sitename},siteurl=#{siteurl},principal=#{principal},phone=#{phone},operateeid=#{operateeid},operatedate=now() where siteid=#{siteid}")
    Integer update(Site site);

}
