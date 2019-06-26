package com.qianqiu.novel.dao;

import java.util.List;

import com.qianqiu.novel.entity.Chapters;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface IChaptersDAO {
    @SelectKey(keyProperty = "chapterid",keyColumn = "chapterid",before = false,resultType = Integer.class,statement = "select max(chapterid) from chapters")
    @Insert("INSERT INTO `novel`.`chapters` (`chapterid`, `chaptername`, `url`, `chapternum`, `state`, `wordnum`, `updatetime`, `rollid`) VALUES (NULL, #{chaptername}, #{url}, #{chapternum}, #{state}, #{wordnum}, now(), #{rollid});")
    Integer add(Chapters chapters);
    @Select("select * from chapters where chaptername = #{param1} and rollid in (select rollid from rolls where bookid=#{param2})")
    Chapters findByName(String chaptername,Integer bookid);
    @Select("select COALESCE(max(chapternum),0) from chapters where rollid in (select rollid from rolls where bookid=#{bookid})")
    Integer getOrder(Integer bookid);
}
