package com.qianqiu.novel.dao;

import java.util.List;

import com.qianqiu.novel.entity.Rolls;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface IRollsDAO {
    @SelectKey(keyProperty = "rollid",keyColumn = "rollid",before = false,resultType = Integer.class,statement = "select max(rollid) from rolls")
    @Insert("INSERT INTO `novel`.`rolls` (`rollid`, `rollname`, `bookid`, `createtime`, `isvip`, `url`) VALUES (NULL, #{rollname}, #{bookid}, now(), #{isvip}, #{url});")
    Integer add(Rolls rolls);
    @Select("select * from rolls where bookid=#{bookid} order by rollid")
    List<Rolls> findByBookid(Integer bookid);
}
