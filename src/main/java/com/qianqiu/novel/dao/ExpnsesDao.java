package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Expnses;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExpnsesDao {
    @Select(value = "select *  from expenses order by exptime desc")
    List<Expnses> query();
    @Insert(value = "INSERT INTO `novel`.`expenses` ( `userid`,`expmoney`, `exptime`)" +
            " VALUES ( #{userid},#{expmoney}, #{exptime})")
    int add(Expnses e);
    @Select(value="select * from expenses where  expid =#{expid}")
    Expnses queryById(Integer expid);
}

