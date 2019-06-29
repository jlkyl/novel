package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Books;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface IBooksDAO {
    @SelectKey(keyProperty = "bookid",keyColumn = "bookid",before = false,resultType = Integer.class,statement = "select max(bookid) from books")
    @Insert("INSERT INTO `novel`.`books` (`bookname`, `cover`, `userid`, `typeid`, `state`, `putaway`, `details`, `createtime`, `url`) VALUES (#{bookname}, #{cover}, #{userid}, #{typeid}, #{state}, #{putaway}, #{details}, now(), #{url});")
    Integer add(Books books);
    @Select("select * from books where bookname = #{bookname}")
    Books findByName(String bookname);
    @Insert("INSERT INTO `novel`.`book_label` (`bookid`, `labelid`) VALUES (#{param1}, #{param2});")
    Integer addLabel(Integer bookid,Integer labelid);
    @Select("select count(*) from chapters where rollid in (select rollid from rolls where bookid=#{bookid})")
    Integer getChapterNums(Integer bookid);
    @Select("select b.*,(select typename from booktype where typeid=b.typeid) typename,(select max(chapternum) from chapters where rollid in (select rollid from rolls where bookid=b.bookid)) chapternum,(select sum(wordnum) from chapters where rollid in (select rollid from rolls where bookid=b.bookid)) wordnum from books b where userid = #{userid}")
    List<Map<String,Object>> findAll(Integer userid);
}
