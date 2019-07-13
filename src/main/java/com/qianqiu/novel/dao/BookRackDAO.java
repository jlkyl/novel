package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.BookRack;
import com.qianqiu.novel.entity.Books;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookRackDAO {
    @Select("<script>select * from\n" +
            "(select br.*,bs.bookname,bs.state,bs.putaway,updatetime,isvip,chapterid,chaptername,\n" +
            "(select pen from users where userid=bs.userid) pen,(select count(*) from bookrack where userid=#{userid}) num, \n" +
            "(select details from books where details = bs.details) details,\n " +
            "(select cover from books where cover = bs.cover) cover, \n" +
            "COALESCE((select sum(wordnum) from chapters where state=0 and rollid in (select rollid from rolls where bookid = bs.bookid)),0) wordcount ,\n" +
            "(select typename from booktype where typeid = bs.typeid) typename from bookrack br\n" +
            "LEFT JOIN books bs on bs.bookid = br.bookid\n" +
            "LEFT JOIN rolls rs on rs.bookid = br.bookid \n" +
            "LEFT JOIN chapters cs on cs.rollid = rs.rollid\n" +
            "where br.userid=#{userid} and chapternum =\n" +
            "(select max(chapternum) from chapters where rollid in (select rollid from rolls where bookid = br.bookid))) a\n" +
            "<if test=\'km!=null\'>where bookname like concat('%',#{km},'%') or pen like CONCAT('%',#{km},'%')</if></script>")
    public List<Map<String, Object>> queryBr(@Param("userid") Integer userid,@Param("km") String km);

    @Delete("delete from bookrack where rackid=#{rackid}")
    public int delete(Integer rackid);
    @Insert("INSERT INTO `novel`.`bookrack` (`rackid`, `userid`, `bookid`, `racktime`) VALUES (#{rackid},#{userid},#{bookid},now())")
    public Integer add(BookRack br);
    @Select("select * from bookrack where userid=#{userid} and bookid=#{bookid}")
    public List<BookRack> queryF(Integer userid,Integer bookid);

    @Select("select count(*) shelvescount from bookrack where userid = #{userid}")
    public Integer queryCount(@Param("userid") Integer userid);
}