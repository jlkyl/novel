package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Chapters;
import org.apache.ibatis.annotations.*;

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
    @Select("select t.*,b.*,r.*,c.* from books b " +
            "LEFT JOIN booktype t on b.typeid=t.typeid " +
            "LEFT JOIN rolls r on r.bookid=b.bookid " +
            "LEFT JOIN chapters c on c.rollid=r.rollid where b.userid=#{userid} GROUP BY t.typeid ")
    List<Map<String,Object>> booksAll(@Param("userid")Integer userid);
    @Select("select * from chapters where chapternum = (select max(chapternum) from chapters " +
            "                       where rollid in (select rollid from rolls where bookid =#{bookid}))")
    Chapters queryTime(@Param("bookid") Integer bookid);
    @Update("update books set putaway=1 where bookid=#{bookid}")
    Integer updBookstate(Integer bookid);
    @Select("select * from books where bookid=#{bookid}")
    Books querybyId(@Param("bookid") Integer bookid);
}
