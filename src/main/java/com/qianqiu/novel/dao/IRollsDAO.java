package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Rolls;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IRollsDAO {
    @SelectKey(keyProperty = "rollid",keyColumn = "rollid",before = false,resultType = Integer.class,statement = "select max(rollid) from rolls")
    @Insert("INSERT INTO `novel`.`rolls` (`rollid`, `rollname`, `bookid`, `createtime`, `isvip`, `url`) VALUES (NULL, #{rollname}, #{bookid}, now(), #{isvip}, #{url});")
    Integer add(Rolls rolls);
    @Select("select *,(select sum(wordnum) from chapters where rollid=r.rollid) wordnum from rolls r where r.bookid=#{bookid} order by r.rollid")
    List<Map<String,Object>> findByBookid(Integer bookid);
    //查询书卷名是否存在
    @Select("select * from rolls where rollname=#{rollname} and bookid=#{bookid}")
    Rolls queryRollname(@Param("rollname") String rollname,@Param("bookid") Integer bookid);
    @Update("update rolls set isvip=#{param1} where rollid=#{param2}")
    Integer updvip(Integer isvip,Integer rollid);
    @Select("select * from rolls where rollid=#{rollid}")
    Rolls queryId(@Param("rollid")Integer rollid);

    @Select("select * from rolls where bookid=#{bookid} order by rollid")
    List<Map<String,Object>> findByBookid001(@Param(("bookid")) Integer bookid);
}
